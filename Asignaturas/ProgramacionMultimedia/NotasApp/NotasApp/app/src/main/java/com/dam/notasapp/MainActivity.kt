package com.dam.notasapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var startAddNotaForResult: ActivityResultLauncher<Intent>
    private lateinit var recyclerViewNotas: RecyclerView
    private lateinit var adaptadorRecyclerViewNotas: AdaptadorNotas
    private lateinit var addNotaButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //bindeo
        recyclerViewNotas=findViewById(R.id.recyclerViewNotas)
        addNotaButton=findViewById(R.id.addNotaBoton)
        //inicializar adaptador
        adaptadorRecyclerViewNotas= AdaptadorNotas()

        recyclerViewNotas.layoutManager= LinearLayoutManager(this)
        recyclerViewNotas.addItemDecoration(DividerItemDecoration(
            this@MainActivity, LinearLayoutManager.VERTICAL
        ))
        recyclerViewNotas.setAdapter(adaptadorRecyclerViewNotas)

        lifecycleScope.launch (Dispatchers.IO) {
            //a recuperar las notas de la base de datos
            val databaseConect= NotaBBDD.getInstance(applicationContext)
            var notas = databaseConect.notaDAO().selectAll()

            withContext(Dispatchers.Main){
            //Notificar al adaptador
                adaptadorRecyclerViewNotas.setNotas(notas)
                
            }

        }



        startAddNotaForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode== Activity.RESULT_OK){
                val data = result.data
                if(data!=null){
                    val tituloNota = data.extras?.getString("titulo")?:""
                    val descNota= data.extras?.getString("descripcion")?:""
                    adaptadorRecyclerViewNotas.addNota(Nota(tituloNota, descNota))
                }
            }
        }

        addNotaButton.setOnClickListener {
            val intent= Intent (this, AddNotaActivity::class.java)
            startAddNotaForResult.launch(intent)
        }

    }
}