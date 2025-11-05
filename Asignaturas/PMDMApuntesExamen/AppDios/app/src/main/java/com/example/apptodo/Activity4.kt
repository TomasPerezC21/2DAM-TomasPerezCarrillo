package com.example.apptodo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_4)

        val listaPersona = listOf (
            Persona("Tomas", "Perez", 26),
            Persona("Alvaro", "Guy", 12),
            Persona("Hugo", "Desi", 21),
            Persona("Jose", "rodriguez", 16),
            Persona("Paco", "Perez", 26),
            Persona("Luis", "Guy", 12),
            Persona("Alex", "Desi", 21),
            Persona("Borja", "rodriguez", 16),
        )

        // RecyclerView
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = AdaptadorRecycler(listaPersona)



    }
}