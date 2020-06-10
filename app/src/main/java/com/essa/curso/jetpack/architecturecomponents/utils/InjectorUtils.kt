package com.essa.curso.jetpack.architecturecomponents.utils

import android.content.Context
import com.essa.curso.jetpack.architecturecomponents.AppExecutor
import com.essa.curso.jetpack.architecturecomponents.data.ArchCompRepository
import com.essa.curso.jetpack.architecturecomponents.data.database.AplicacionRoomDatabase
import com.essa.curso.jetpack.architecturecomponents.data.network.WebService
import com.essa.curso.jetpack.architecturecomponents.ui.architecturecomp.MainViewModelFactory

/*ESTA CLASE SIMPLEMENTE SABE COMO INSTANCIAR LOS OBJETOS*/
class InjectorUtils {
    //SINGLETON
    companion object{
        fun inyectarRepositorio(context: Context): ArchCompRepository{
            val database = AplicacionRoomDatabase.getInstance( context.applicationContext )
            val executor  = AppExecutor.getInstance()
            val webService = WebService.getInstance( context )
            return  ArchCompRepository.getInstance( database?.companieroDao()!!, executor, webService)
        }

        fun inyectarArchConViewModelFactory(context: Context): MainViewModelFactory{
            val repository = inyectarRepositorio(context.applicationContext)
            return  MainViewModelFactory(repository)
        }
    }
}
