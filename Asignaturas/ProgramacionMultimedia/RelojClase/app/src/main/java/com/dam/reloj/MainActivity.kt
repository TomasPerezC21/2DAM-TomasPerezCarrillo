package com.dam.reloj

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

        //variable de las shared preferencias
        val prefs = getSharedPreferences("Preferencias", MODE_PRIVATE)

        //escritura del SP
        val editor = prefs.edit()
        editor.putString("nombre", "Daniel")
        editor.apply()

        val nombreSP = prefs.getString("nombre", "Paco")
        Log.d("Shared", nombreSP.toString())

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.iniciarReloj()

        val textView: TextView = findViewById(R.id.textView)

//        viewModel.hora().observe(this){
//            textView.text = "$it"
//        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.hora.collect { hora ->
                    textView.text = hora
                }
            }
        }

        textView.setOnClickListener { viewModel.startStopReloj() }
    }
}