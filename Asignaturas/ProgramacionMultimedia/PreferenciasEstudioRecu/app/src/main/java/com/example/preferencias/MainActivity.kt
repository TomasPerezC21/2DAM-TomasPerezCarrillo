package com.example.preferencias

import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

class MainActivity : AppCompatActivity() {

    private lateinit var swAll: MaterialSwitch
    private lateinit var swEmail: MaterialSwitch
    private lateinit var swSMS: MaterialSwitch
    private lateinit var swClash: MaterialSwitch

    private lateinit var tvResumen: TextView

    private lateinit var textoResumen: StringBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        //bindeo
        swAll = findViewById(R.id.swNotificacionesAll)
        swClash = findViewById(R.id.swClash)
        swEmail = findViewById(R.id.swEmail)
        swSMS = findViewById(R.id.swSMS)
        tvResumen = findViewById(R.id.tvResumen)

        //oyentes

        swAll.setOnClickListener {
            toggleAllNotifications(swAll.isChecked)
        }

        swClash.setOnCheckedChangeListener {
            _, isChecked ->
            checkAllNotifications()
            generarResumen()
        }

        swEmail.setOnCheckedChangeListener {
                _, isChecked ->
            checkAllNotifications()
            generarResumen()
        }

        swSMS.setOnCheckedChangeListener {
                _, isChecked ->
            checkAllNotifications()
           generarResumen()
        }


    }

    fun toggleAllNotifications(b: Boolean){
        if (swAll.isChecked){
            swSMS.isChecked=true
            swEmail.isChecked=true
            swClash.isChecked=true
        }
        else{
            swSMS.isChecked=false
            swEmail.isChecked=false
            swClash.isChecked=false
        }

    }

    fun checkAllNotifications(){
        if (swClash.isChecked && swEmail.isChecked && swSMS.isChecked){
            swAll.isChecked = true
        }else{
            swAll.isChecked = false
        }
    }

    fun generarResumen(){

        val sb = StringBuilder("Notificaciones seleccionadas: ")
        val listaSeleccionada = mutableListOf<String>()

        if (swEmail.isChecked) listaSeleccionada.add("Correo electrónico")
        if (swSMS.isChecked) listaSeleccionada.add("SMS")
        if (swClash.isChecked) listaSeleccionada.add("Clash")

        if (listaSeleccionada.isEmpty()) {
            sb.append("Ninguna")
        } else {
            sb.append(listaSeleccionada.joinToString(", "))
        }

        tvResumen.text = sb.toString()

    }

}