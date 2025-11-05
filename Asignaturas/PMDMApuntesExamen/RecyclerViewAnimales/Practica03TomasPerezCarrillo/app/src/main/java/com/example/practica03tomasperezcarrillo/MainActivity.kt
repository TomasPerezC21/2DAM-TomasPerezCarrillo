package com.example.practica03tomasperezcarrillo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lista de animales
        val animales = listOf(
            Animales("Conejo", "Herbívoro", "Vivíparo"),
            Animales("Gallina", "Omnívoro", "Ovíparo"),
            Animales("Águila", "Carnívoro", "Ovíparo"),
            Animales("Jirafa", "Herbívoro", "Vivíparo"),
            Animales("Cocodrilo", "Carnívoro", "Ovíparo"),
            Animales("Elefante", "Herbívoro", "Vivíparo"),
            Animales("Gato", "Carnívoro", "Vivíparo"),
            Animales("Ratón", "Omnívoro", "Vivíparo"),
            Animales("Murciélago", "Omnívoro", "Vivíparo"),
            Animales("Perro", "Carnívoro", "Vivíparo"),
            Animales("Pingüino", "Carnívoro", "Ovíparo"),
            Animales("Abeja", "Herbívoro", "Ovíparo")
        )

        // RecyclerView
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = AnimalesAdapter(animales)
    }
}