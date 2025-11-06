package com.dam.exnov25

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

class AjustesActivity : AppCompatActivity() {

    private lateinit var swLactosa: MaterialSwitch
    private lateinit var swFrutosSecos: MaterialSwitch
    private lateinit var swGluten: MaterialSwitch

    private lateinit var swGeneral: MaterialSwitch

    private lateinit var textoResumen: TextView

    private lateinit var textoNombre: EditText

    private lateinit var checkBoxEmergencia: CheckBox

    private lateinit var omnivoro: RadioButton
    private lateinit var vegetario: RadioButton
    private lateinit var vegano: RadioButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ajustes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        //Bindeo de switches
        swGeneral = findViewById(R.id.swAll)
        swLactosa = findViewById(R.id.swLactosa)
        swFrutosSecos = findViewById(R.id.swFruSec)
        swGluten = findViewById(R.id.swGluten)

        //Bindeo de texto resumen
        textoResumen=findViewById(R.id.tvResumen)

        //Bindeo texto nombre

        textoNombre = findViewById(R.id.etNombre)

        //Bindeo checkbox emergencia
        checkBoxEmergencia = findViewById(R.id.checkSeleccion)

        checkBoxEmergencia.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                textoResumen.text = generarResumen()
            }
        }

        textoNombre.setOnClickListener {
            textoResumen.text = generarResumen()
        }

        //Oyente de swGeneral
        swGeneral.setOnClickListener {
            marcarTodos(swGeneral.isChecked)
            textoResumen.text = generarResumen()
        }

        //oyentes resto botones
        swLactosa.setOnCheckedChangeListener { _,isChecked ->
            if (isChecked){
                verificarSwitches()
                textoResumen.text = generarResumen()
            }
        }
        swGluten.setOnCheckedChangeListener { _,isChecked ->
            if (isChecked){
                verificarSwitches()
                textoResumen.text = generarResumen()
            }
        }

        swFrutosSecos.setOnCheckedChangeListener { _,isChecked ->
            if (isChecked){
                verificarSwitches()
                textoResumen.text=generarResumen()
            }
        }

        omnivoro = findViewById(R.id.rbOmnivoro)
        vegetario = findViewById(R.id.rdVegetariano)
        vegano = findViewById(R.id.rbVegano)


        omnivoro.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                textoResumen.text = generarResumen()
            }
        }

        vegetario.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                textoResumen.text = generarResumen()
            }
        }

        vegano.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                textoResumen.text = generarResumen()
            }
        }

    }

    /**
     * Verifica si todos los switches (no maestro) están activados. Si todos están activados,
     * activa el switch maestro (`swAll`). De lo contrario, lo desactiva.
     */
    fun verificarSwitches() {
        if (swGluten.isChecked && swLactosa.isChecked && swFrutosSecos.isChecked){
            swGeneral.isChecked = true
        }
    }

    /**
     * Configura el estado de los tres MaterialSwitch al valor proporcionado.
     *
     * @param b Si es `true`, todos los switches se marcan como seleccionados.
     *          Si es `false`, se desmarcan.
     */
    private fun marcarTodos(b: Boolean) {
            swLactosa.isChecked = b
            swGluten.isChecked = b
            swFrutosSecos.isChecked = b
    }


    /**
     * Actualiza tvResumen con la información marcada. Se facilita el string de las alergias seleccionadas.
     * Falta recuperar el resto de componentes y mostrarlo.
     * @return Devuelve un string con el texto a mostrar
     */
    private fun generarResumen(): String {

        //codigo que genera String con las alergias seleccionadas
        val alergias = mutableListOf<String>()
        if (swLactosa.isChecked) alergias.add("lactosa")
        if (swFrutosSecos.isChecked) alergias.add("frutos secos")
        if (swGluten.isChecked) alergias.add("gluten")
        val alergia = ". En caso de emergencia llamar a ambulancia."
        if (omnivoro.isChecked) alergias.add("Es omnivoro")
        if (vegetario.isChecked) alergias.add("Es vegetariano")
        if (vegano.isChecked) alergias.add("Es vegano")
        val nombre = textoNombre.text.toString()

        val alergiaTexto = if (alergias.isEmpty()) "$nombre no tiene alergias" else "$nombre tiene alergia a ${alergias.joinToString(" y ")}"

        if (checkBoxEmergencia.isChecked) {
            return alergiaTexto + alergia
        }

        return alergiaTexto
    }
}