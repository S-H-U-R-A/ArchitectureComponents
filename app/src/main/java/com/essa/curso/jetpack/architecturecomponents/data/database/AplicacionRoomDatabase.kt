package com.essa.curso.jetpack.architecturecomponents.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [ ( CompanieroEntity::class )], version = 1, exportSchema = false)
abstract class AplicacionRoomDatabase : RoomDatabase(){

    abstract  fun companieroDao(): CompanieroDao

    companion object{

        private var INSTANCE: AplicacionRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context):  AplicacionRoomDatabase? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                           AplicacionRoomDatabase::class.java,
                          "miapp.db")
                            .allowMainThreadQueries()
                            .build()
            }
            return  INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }

    }

}