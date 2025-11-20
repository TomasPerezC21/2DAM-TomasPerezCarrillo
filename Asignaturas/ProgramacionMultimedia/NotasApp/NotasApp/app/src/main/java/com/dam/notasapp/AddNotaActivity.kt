package com.dam.notasapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddNotaActivity : AppCompatActivity() {

    private lateinit var etTitulo: EditText
    private lateinit var etDesc: EditText
    private lateinit var buttonAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_nota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //bindeo
        etTitulo=findViewById(R.id.editTextTituloNota)
        etDesc=findViewById(R.id.editTextDescripcionNota)
        buttonAdd=findViewById(R.id.buttonAddNota)

        //oyente

        buttonAdd.setOnClickListener {
            if (etTitulo.text.isNotEmpty() && etDesc.text.isNotEmpty()){
                //le paso la info a la actividad principal
                val resultIntent= Intent()
                resultIntent.putExtra("titulo", etTitulo.text.toString())
                resultIntent.putExtra("descripcion", etDesc.text.toString())
                setResult(RESULT_OK, resultIntent)
                finish()
            }else{
                //hay algún campo vacío
                Toast.makeText(this, "Hay algún campo vaciío", Toast.LENGTH_SHORT).show()
            }
        }

    }
}