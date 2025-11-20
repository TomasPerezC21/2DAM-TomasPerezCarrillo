package com.dam.myapplication

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "settings")

class SharedPreferencesViewModel: ViewModel() {



    companion object{
        val NOMBRE_KEY= stringPreferencesKey("nombre")
        val EMAIL_KEY= stringPreferencesKey("email")
    }

    fun getNombre(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[NOMBRE_KEY] ?: "Sin nombre"
        }
    }

    fun setNombre(context: Context, nombre: String) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[NOMBRE_KEY] = nombre
            }
        }
    }



}