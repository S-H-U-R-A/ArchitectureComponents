package com.essa.curso.jetpack.architecturecomponents.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

// TABLA COMPAÑERO
// |    id  |   nombre  |   compañia    |   email   |   telf    |

@Entity( tableName = "companiero")
data class CompanieroEntity (
    @PrimaryKey(autoGenerate = false) @NotNull val id: String,
    @NotNull val nombre: String,
    @NotNull val compania: String,
    @NotNull val email: String,
    @NotNull val telf: String
)