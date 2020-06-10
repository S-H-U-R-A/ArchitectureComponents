package com.essa.curso.jetpack.architecturecomponents

import androidx.annotation.MainThread
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.Executors

class AppExecutor {

    private var mDisco: Executor? = null
    private var mHiloPrincipal: Executor? = null
    private var mNetwork: Executor? = null

    constructor(disco: Executor, network: Executor){
        this.mDisco = disco;
        this.mNetwork = network;
    }

    //SE CREA UN SINGLETON ES DECIR UN OBJETO QUE SOLO SE INICIALIZA UNA VEZ
    companion object{

        private var mIntancia: AppExecutor? = null

        @Synchronized
        fun getInstance(): AppExecutor{
            if (mIntancia == null){
                mIntancia = AppExecutor(
                                Executors.newSingleThreadExecutor(),
                                Executors.newFixedThreadPool(3)
                            )
            }
            return this.mIntancia!!
        }
    }

    fun hiloDisco(): Executor? {
        return this.mDisco;
    }

    fun hiloPrincipal(): Executor?{
        return this.mHiloPrincipal;
    }

    fun hiloNetwork(): Executor?{
        return this.mNetwork;
    }


}