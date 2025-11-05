package com.dam.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    //Creacion de variables
    private lateinit var et1: EditText

    private lateinit var et2: EditText

    private lateinit var result: TextView
    private lateinit var botonSuma: Button

    private lateinit var botonResta: Button
    private lateinit var botonMulti: Button
    private lateinit var botonDiv: Button

    private lateinit var botonSpinner: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Asignacion de las partes del layout
        et1 = findViewById(R.id.Texto1)

        et2 = findViewById(R.id.Texto2)

        result = findViewById(R.id.resultado)




        //Asignación a cada botón
        findViewById<Button>(R.id.botonSuma).setOnClickListener { operar("suma") }

        findViewById<Button>(R.id.botonResta).setOnClickListener { operar("resta") }
        findViewById<Button>(R.id.botonMulti).setOnClickListener { operar("multi") }
        findViewById<Button>(R.id.botonDiv).setOnClickListener { operar("div") }

        //Para quitar el texto al hacer clic
        et1.setOnClickListener { et1.text.clear() }
        et2.setOnClickListener { et2.text.clear() }


        botonSuma = findViewById(R.id.botonSuma)


        botonSpinner = findViewById(R.id.botonSpinner)

        botonSpinner.setOnClickListener {

            val intent = Intent(this, SpinnerPrueba::class.java)
            startActivity(intent)
        }


    }
    //metodo que realiza las operaciones en base a la variable que envía cada botón
    private fun operar(operacion: String) {

        val numero1 = et1.text.toString()
        val numero2 = et2.text.toString()

        if (numero1.isBlank() || numero1.isEmpty() || numero2.isBlank() || numero2.isEmpty()){

            result.text = "Tienes que ingresar ambos números"

            return
        }

        val num1 = numero1.toDouble()
        val num2 = numero2.toDouble()

        var resultado: Double

        if  (operacion == "suma"){
            resultado = num1 + num2
            result.text = resultado.toString()
            Toast.makeText(this, "hola mundo", Toast.LENGTH_SHORT).show()
        }

        if (operacion == "resta"){
            resultado = num1 - num2
            result.text = resultado.toString()
        }

        if (operacion == "multi"){
            resultado = num1 * num2
            result.text = resultado.toString()
        }

        if (operacion == "div") {

            if (num2 == 0.0) {
                result.text = "No se puede dividir entre 0"
            } else {
                resultado = num1 / num2
                result.text = resultado.toString()


            }

        }
}
}