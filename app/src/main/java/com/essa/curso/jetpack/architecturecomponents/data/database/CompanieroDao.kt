package com.essa.curso.jetpack.architecturecomponents.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompanieroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Companiero: CompanieroEntity)

    @Query("DELETE FROM companiero")
    fun deleteAll()

    @Query("SELECT * FROM companiero")
    fun selectAllCompanieros(): List<CompanieroEntity>

}