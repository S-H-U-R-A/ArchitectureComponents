package com.essa.curso.jetpack.architecturecomponents.data

import androidx.lifecycle.MutableLiveData
import com.essa.curso.jetpack.architecturecomponents.AppExecutor
import com.essa.curso.jetpack.architecturecomponents.data.database.CompanieroDao
import com.essa.curso.jetpack.architecturecomponents.data.database.CompanieroEntity
import com.essa.curso.jetpack.architecturecomponents.data.network.WebService
import kotlin.math.min

//REPOSITORIO ENCARGADO DE LOS PROCESOS DE DATA, PARA QUE ESTO SEA TRANSPARENTE PARA LAS VISTAS
//ESTE ARCHIVO ES EL ENCARGADO DE QUE LAS VISTAS NI SE ENTEREN DEL ORIGEN DE LOS DATOS

class ArchCompRepository (
    //VARIABLE DE EXCECUCION DE HILOS
    var executor: AppExecutor,
    //EL DAO ES EL QUE SABE QUE OPERACIONES SE HACEN EN LA  BASE DE DATOS
    //BASICAMENTE ES UN MAPEO O SERIALIZACION DE LA BASE DE DATOS
    var companieroDao: CompanieroDao,
    var webService: WebService
){

    //VARIABLE PARA OBSERVAR LOS CAMBIOS EN LA LISTA DE COMPAÑEROS
    val companierosObs = MutableLiveData<List< CompanieroEntity>>()

    init {
        //ACA SE SOLICITAN LOS DATOS DE LOS COMPAÑEROS
        val networkData = webService.getCompanieros()
        //SE USA FOREVER LO CUAL ES COMO ASYNC  EN TYPESCRIPT QUE BASICAMENTE LE DICE QUE ESTE
        //OYENTE U OBSERVANDO HASTA QUE LOS DATOS LLEGUEN
        networkData.observeForever { newDataFromServer ->
            //SE LLAMA EL HILO DE ACCESO A DISCO
            executor.hiloDisco()?.execute {
                //SE ELIMINAN TODOS LOS DATOS VIEJOS
                companieroDao.deleteAll()
                //Y SE RECORRE Y ALMACENA LA NUEVA DATA DE COMPAÑEROS
                newDataFromServer?.forEach{
                    companieroDao.insert(it)
                }
                //POR ULTIMO SE DISPARA EL EVENTO PARA QUE LOS OBSERVADORES
                getInfoBaseDatos();
            }
        }
    }

    private fun getInfoBaseDatos(){
        //ENCARGADA DE COMUNICAR A LOS OBSERVADORES EL CAMBIO EN LA LISTA MUTABLE
        companierosObs.postValue(  companieroDao.selectAllCompanieros() )
    }

    //PATRON SINGLETON
    companion object{

        private var mInstance: ArchCompRepository? = null
        //LA IDEA ES RETORNAR UNA SOLA INSTANCIA DEL REPOSITORIO
        @Synchronized
        fun getInstance(
            companieroDao: CompanieroDao,
            executor: AppExecutor,
            webService: WebService
        ): ArchCompRepository{
            if(mInstance == null){
                mInstance = ArchCompRepository(executor, companieroDao, webService)
            }
            return  mInstance as ArchCompRepository
        }

    }


}