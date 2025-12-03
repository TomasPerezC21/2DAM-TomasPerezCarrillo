package com.example.ejercicio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RutinasAdapter(private val lista: List<String>) : RecyclerView.Adapter<RutinasAdapter.MiViewHolder>() {

    // A. VIEWHOLDER
    class MiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tvEjercicio)
    }

    // B. ON CREATE (Inflar diseño de fila)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_ejercicios, parent, false)
        return MiViewHolder(view)
    }

    // C. ON BIND (Poner datos)
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val ejercicio = lista[position]
        holder.titulo.text = ejercicio

        // GESTIÓN DEL CLIC (Añade esto si te lo piden)
        holder.itemView.setOnClickListener {
            // Opción A: Toast simple (Necesitas el contexto de la vista)
            Toast.makeText(holder.itemView.context, "Elegiste: $ejercicio", Toast.LENGTH_SHORT).show()

            // Opción B: Si te piden abrir otra Activity, se complica un poco más,
            // pero normalmente con un Toast vale para demostrar que sabes capturar el evento.
        }
    }

    // D. GET ITEM COUNT
    override fun getItemCount() = lista.size
}
