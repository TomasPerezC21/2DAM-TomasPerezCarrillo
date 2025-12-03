package com.dam.exnov25

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

class AjustesActivity : AppCompatActivity() {

    private lateinit var swLactosa: MaterialSwitch
    private lateinit var swFrutosSecos: MaterialSwitch
    private lateinit var swGluten: MaterialSwitch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ajustes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * Verifica si todos los switches (no maestro) están activados. Si todos están activados,
     * activa el switch maestro (`swAll`). De lo contrario, lo desactiva.
     */
    fun verificarSwitches() {
    }

    /**
     * Configura el estado de los tres MaterialSwitch al valor proporcionado.
     *
     * @param b Si es `true`, todos los switches se marcan como seleccionados.
     *          Si es `false`, se desmarcan.
     */
    private fun marcarTodos(b: Boolean) {

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
        val alergiaTexto = if (alergias.isEmpty()) "no tiene alergias" else "tiene alergia a ${alergias.joinToString(" y ")}"

        return ""
    }
}