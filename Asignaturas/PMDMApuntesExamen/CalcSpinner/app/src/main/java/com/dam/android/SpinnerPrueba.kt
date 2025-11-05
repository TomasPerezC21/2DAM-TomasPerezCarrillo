package com.dam.android

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SpinnerPrueba : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_prueba)

        //val opciones = listOf("Opcion 1", "Opcion 2", "Opcion 3", "Opcion 4")

        //val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)

        val adaptador=ArrayAdapter.createFromResource(this, R.array.valores_spinner, android.R.layout.simple_spinner_item)


        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val miSpinner = findViewById<Spinner>(R.id.miSpinner)

        miSpinner.adapter = adaptador

        miSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // 'position' es el índice del ítem seleccionado (0, 1, 2...)
                //val opcionSeleccionada = opciones[position]

                val opcionSeleccionada = parent.getItemAtPosition(position)

                // Muestra un Toast con la selección
                Toast.makeText(
                    this@SpinnerPrueba,
                    "Has seleccionado: $opcionSeleccionada",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se suele usar, pero debe estar aquí
            }
        }
    }
}