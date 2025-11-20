package com.dam.notasapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nota(
    var titulo:String,
    var descripcion:String,

    @PrimaryKey(autoGenerate = true)
    val id:Int=0
) {

}