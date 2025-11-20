package com.dam.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dam.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var email: EditText

    private lateinit var btReiniciar: Button

    private lateinit var btCargar: Button

    private lateinit var btGuardar: Button

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nombre= findViewById(R.id.editTnombre)
        email=findViewById(R.id.editTemail)
        btReiniciar=findViewById(R.id.buttonReiniciar)
        btCargar=findViewById(R.id.buttonCargar)
        btGuardar=findViewById(R.id.buttonGuardar)

        btReiniciar.setOnClickListener {
            nombre.text.clear()
            email.text.clear()

//            val shPref = getSharedPreferences("Preferencias", MODE_PRIVATE)
//            shPref.edit{
//                putString("nombre","")
//                putString("email","")
//            }
        }

        btCargar.setOnClickListener {
            val shPref = getSharedPreferences("Preferencias", MODE_PRIVATE)
            val spNombre = shPref.getString("nombre", "")
            nombre.setText(spNombre)
            val spEmail = shPref.getString("email","")
            email.setText(spEmail)
        }

        btGuardar.setOnClickListener {
            val shPref = getSharedPreferences("Preferencias", MODE_PRIVATE)
            shPref.edit{
                putString("nombre",nombre.text.toString())
                putString("email",email.text.toString())
            }
        }

        val viewModelSP = ViewModelProvider(this)[SharedPreferencesViewModel::class.java]
        lifecycleScope.launch {
            viewModelSP.getNombre(this@MainActivity).collect { name ->
                nombre.setText(name)
            }
        }
        viewModelSP.setNombre(this@MainActivity, "Daniel")
    }


    override fun onResume() {
        super.onResume()
        val shPref = getSharedPreferences("Preferencias", MODE_PRIVATE)
        val spNombre = shPref.getString("nombre", "")
        nombre.setText(spNombre)
        val spEmail = shPref.getString("email","")
        email.setText(spEmail)
    }




    override fun onPause() {
        super.onPause()
        val shPref = getSharedPreferences("Preferencias", MODE_PRIVATE)
        shPref.edit {
            putString("nombre", nombre.text.toString())
        }
    }


}