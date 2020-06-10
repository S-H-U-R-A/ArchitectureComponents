package com.essa.curso.jetpack.architecturecomponents.ui.architecturecomp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.essa.curso.jetpack.architecturecomponents.data.ArchCompRepository
import com.essa.curso.jetpack.architecturecomponents.data.database.CompanieroEntity


//EL VIEW MODEL HACE QUE LOS DATOS PERDUEREN AL CICLO DE VIDA DE LAS VISTAS
class ArchitectureCompViewModel(
    var mRepository: ArchCompRepository
    ) : ViewModel() {

    //SE CREA LA LISTA MUTABLE O ACTUALIZABLE
    val mListaCompanieros = MutableLiveData<List<CompanieroEntity>>()

    init {
        //AHORA NOS ESTAMOS SUSCRIBIENDO A ESTA OTRA VARIABLE LIVE DATA  PARA ESTAR PENDIENTES
        //DE LOS CAMBIOS EN LA LISTA
        mRepository.companierosObs.observeForever { companierosCambio ->
            //LOS DATOS DE LA LISTA SOLICITADA AHORA DE VAN COLOCANDO EN ESTA LISTA
            //DEL VIEW MODEL PARA QUE SEA OBSERVADA DESPUES POR PARTE DE OTRO ELEMENTO
            //BASICAMENTE CON LOS LIVEDATA LO QUE SE HACE ES UN ACARREO
            //DE VARIABLES OBSERVABLES Y OBSERVADORES DESDE EL ORIGEN DE LOS DATOS
            //HASTA LLEGAR A LAS VISTAS
            mListaCompanieros.postValue(companierosCambio)
        }
    }

}
