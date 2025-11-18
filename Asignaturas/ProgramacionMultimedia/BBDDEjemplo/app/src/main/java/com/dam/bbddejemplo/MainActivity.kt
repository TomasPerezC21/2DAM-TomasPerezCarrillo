package com.dam.bbddejemplo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        lifecycleScope.launch(Dispatchers.IO) {
            // acción a ejecutar en segundo plano
            val database = UsuariosBBDD.getInstance(applicationContext)

            database.usuarioDAO().insert(Usuario("Daniel", "daniel.amiguet@murciaeduca.es"))

            val usuario = database.usuarioDAO().getByEmail("daniel.amiguet@murciaeduca.es")
            if(usuario!=null){
                database.usuarioDAO().delete(usuario)
            }


        }

    }
}