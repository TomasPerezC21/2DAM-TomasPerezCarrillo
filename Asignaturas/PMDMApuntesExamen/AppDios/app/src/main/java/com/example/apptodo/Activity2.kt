package com.example.apptodo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity2 : AppCompatActivity() {

    private lateinit var spinner: Spinner

    private lateinit var radioGroup: RadioGroup
    private lateinit var resultRadioGroup: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)

        //Bindeo de la toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "TOOLBAR activity 2"

        //Bindeo
        spinner = findViewById(R.id.spinner)

        radioGroup = findViewById(R.id.radioGroup1)
        resultRadioGroup = findViewById(R.id.resulgroup)

        //Array con valores del spinner
        val opciones = arrayOf("Activity 1", "Activity 2", "Activity 3")

        //Adaptador
        val adaptadorSpinner = ArrayAdapter(
            this, // contexto (la Activity)
            android.R.layout.simple_spinner_item, // layout predefinido por Android
            opciones // array de datos
        )

        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adaptadorSpinner

        spinner.setSelection(0, false)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val temaSeleccionado = parent.getItemAtPosition(position).toString()

                if (temaSeleccionado.equals(opciones.get(0))){
                     val intent = Intent(this@Activity2, ActivityPrincipal::class.java)
                    startActivity(intent)
                }

                if (temaSeleccionado.equals(opciones.get(2))){
                    val intent = Intent(this@Activity2, Activity3::class.java)
                    startActivity(intent)
                }


                Toast.makeText(this@Activity2, "Elegiste: $temaSeleccionado", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioSeleccionado = findViewById<RadioButton>(checkedId)
            resultRadioGroup.text = "Has elegido: ${radioSeleccionado.text}"
        }

    }
}