package com.essa.curso.jetpack.architecturecomponents.data.network

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.essa.curso.jetpack.architecturecomponents.data.database.CompanieroEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WebService(context: Context) {
    //SE CREA UNA ARREGLO DINAMICO DE COMPAÑEROS
    private var mCompaniero: ArrayList<CompanieroEntity>

    //EN EL CONSTRUCTOR DE LA CLASE SE HACE LA LECTURA DEL ARCHIVO JSON QUE CONTIENE LA DATA
    //DE LOS COMPAÑEROS
    init{
        val equipoFile = context.assets.open("equipo.json");
        val size = equipoFile.available()
        val buffer = ByteArray(size)
        equipoFile.read(buffer)
        equipoFile.close()
        val jsonString = String(buffer)

        val gson = Gson()
        val collectionType = object : TypeToken<List<CompanieroEntity>>() {}.type
        //AL FINAL SE ESTA ALMACENANDO LA DATA RECIBIDA EN FORMATO JSON
        //AHORA CON EL FORMATO DEL MODEL DE DATOS DE COMPANIEROENTITY
        mCompaniero = gson.fromJson(jsonString, collectionType)
    }

    //PATRON SINGLETON PARA CREAR UNA SOLA INSTANCIA
    companion object{
        private var mInstance: WebService? = null

        @Synchronized
        fun getInstance(context: Context): WebService {
            //SI NO HAY INSTANCIA SE CREA UNA NUEVA
            if(mInstance == null){
                mInstance = WebService(context)
            }
            //PERO SI EXISTE SE DEVUELVE LA QUE PREVIAMENTE YA FUERA CREADA
            return  this.mInstance!!
        }

    }

    fun getCompanieros(): MutableLiveData<List<CompanieroEntity>>{
        //VARIABLE QUE ALMACENA UNA LISTA MUTABLE O ACTUALIZABLE DE COMPAÑEROS
        val liveData = MutableLiveData<List<CompanieroEntity>>()
        //SI HAY OBSERVADORES SERAN AVISADOS CUANDO A LA LISTA SE LE AGREGUEN
        //NUEVOS VALORES
        liveData.postValue(mCompaniero)
        //RETORNA LA LISTA
        return liveData
    }

}