package com.essa.curso.jetpack.architecturecomponents.ui.architecturecomp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.essa.curso.jetpack.architecturecomponents.data.ArchCompRepository

//PARA CREAR VIEW MODELS MAS COMPLEJOS SE DEBEN CREAR ASI COMO ACA FACTORIAS EN LAS
//CUALES MODIFICAMOS EL CONSTRUCTOR
class MainViewModelFactory(
    private val mRepository: ArchCompRepository
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArchitectureCompViewModel(mRepository) as T
    }
}