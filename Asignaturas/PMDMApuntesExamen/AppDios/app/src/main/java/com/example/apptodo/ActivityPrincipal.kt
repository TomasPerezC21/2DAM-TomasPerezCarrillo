package com.example.apptodo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.widget.Toolbar

class ActivityPrincipal : AppCompatActivity() {

    private lateinit var numero1: EditText

    private lateinit var numero2: EditText

    private lateinit var resultado: TextView

    private lateinit var botonSuma: Button

    private lateinit var botonResta: Button

    private lateinit var botonMulti: Button

    private lateinit var botonDiv: Button

    private lateinit var checkBox: CheckBox
    private lateinit var textoBox: TextView

    private lateinit var switch1: Switch
    private lateinit var textoSwitch: TextView

    private lateinit var botonAct2: Button



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        //Bindeo de la toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "TOOLBAR"

        //flecha
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //Bindeo de la calculadora

        numero1 =findViewById(R.id.numero1)
        numero2 = findViewById(R.id.numero2)

        resultado = findViewById(R.id.resultadoMostrar)

        botonSuma = findViewById(R.id.button1)
        botonResta = findViewById(R.id.button2)
        botonMulti = findViewById(R.id.button3)
        botonDiv = findViewById(R.id.button4)

        //Bindeo del checkbox
        checkBox = findViewById(R.id.checkBox1)
        textoBox = findViewById(R.id.checkboxTexto)

        //Bindeo del switch
        switch1 = findViewById(R.id.switch2)
        textoSwitch = findViewById(R.id.textViewSwitch)

        //Bindeo boton segunda actividad
        botonAct2 = findViewById(R.id.buttonAct2)


        //Listeners para cada boton y asignar accion en metodo aparte
        botonSuma.setOnClickListener {calculadora("suma")
            Toast.makeText(this, "Vamos a sumar", Toast.LENGTH_SHORT).show()
        }

        botonResta.setOnClickListener { calculadora("resta") }
        botonMulti.setOnClickListener { calculadora("multi") }
        botonDiv.setOnClickListener { calculadora("div") }



        //Listener del checkbox
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Activado mi loco", Toast.LENGTH_SHORT).show()
                textoBox.text = "Tamos activos"
            }else{
                Toast.makeText(this, "Desactivado mi loco", Toast.LENGTH_SHORT).show()
                textoBox.text = "Tamos desactivados"
            }
        }

        //Listener del switch
        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                textoSwitch.text = "Switch marcado"
                Toast.makeText(this, "Switch marcado mi loco", Toast.LENGTH_SHORT).show()
            }else{
                textoSwitch.text = "Switch desmarcado"
                Toast.makeText(this, "Switch desmarcado mi loco", Toast.LENGTH_SHORT).show()
            }
        }

        //Listener boton act2 con su intent para cambiar a la actividad2

        botonAct2.setOnClickListener {
            //Cambiar a otra activity pa verla
            val intent = Intent(this, Activity4::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Infla el menú; esto añade los ítems a la barra de acción si está presente.
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Maneja los clics de los ítems de la barra de acción
        return when (item.itemId) {

            android.R.id.home -> {
                // Esta es la forma moderna y correcta de "ir atrás"
                onBackPressedDispatcher.onBackPressed()
                true // Indica que hemos manejado el clic
            }

            // Este ID viene de tu archivo XML
            R.id.configuracion -> {
                // Aquí pones el código para "Configuración"
                Toast.makeText(this, "Has pulsado Configuración", Toast.LENGTH_SHORT).show()
                true
            }
            // Este ID también viene de tu XML
            R.id.action_info -> {
                // Aquí pones el código para "Acerca de"
                Toast.makeText(this, "Has pulsado Acerca de", Toast.LENGTH_SHORT).show()
                true
            }
            // Si no es ninguno de los nuestros, deja que el sistema lo maneje
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun calculadora(opcion: String) {

        //Asigno el texto del usuario a un numero
        var numero1String = numero1.text.toString()
        var numero2String = numero2.text.toString()

        //Paso ese texto a un valor numérico
        var num1 = numero1String.toDoubleOrNull()
        var num2 = numero2String.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            resultado.text = "Error: Entrada inválida"
            return
        }

        val resultadoDouble = when (opcion) {
            "suma" ->  num1 + num2
            "resta" ->  num1 - num2
            "multi" ->  num1 * num2
            "div" -> {
                // 4. MEJORA: Manejar la división por cero
                if (num2 == 0.0) {
                    resultado.text = "Error: No se puede dividir por cero"
                    return // Salimos de la función
                } else {
                    num1 / num2
                }
            }
            else -> {
                resultado.text = "Error: Operación desconocida"
                return
            }

        }

        resultado.text = resultadoDouble.toString()


    }



}