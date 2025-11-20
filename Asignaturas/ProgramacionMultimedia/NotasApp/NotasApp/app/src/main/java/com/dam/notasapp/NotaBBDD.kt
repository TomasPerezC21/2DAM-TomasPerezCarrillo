package com.dam.notasapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nota::class], version = 1, exportSchema = false)
abstract class NotaBBDD: RoomDatabase(){

    abstract fun notaDAO(): NotaDAO
    companion object DatabaseBuilder{
        private var INSTANCE : NotaBBDD ? = null

        fun getInstance (context: Context): NotaBBDD {
            if (INSTANCE == null) synchronized(Nota::class) {
                INSTANCE = buildRoomDB(context)
            }
            return INSTANCE!!
        }


        private fun buildRoomDB (contexto : Context) =
            Room.databaseBuilder (
                contexto.applicationContext, NotaBBDD::class.java, "notas.db"
            ).build ()
    }
}
