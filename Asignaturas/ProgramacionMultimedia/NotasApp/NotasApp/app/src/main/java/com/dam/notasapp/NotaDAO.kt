package com.dam.notasapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotaDAO {

    @Insert
    fun insertNote(nota: Nota): Long

    @Delete
    fun delete(nota:Nota)

    @Query("Select * from nota")
    fun selectAll(): List<Nota>


}