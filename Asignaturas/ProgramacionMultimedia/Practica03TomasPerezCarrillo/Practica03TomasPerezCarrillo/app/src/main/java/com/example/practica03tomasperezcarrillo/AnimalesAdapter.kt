package com.example.practica03tomasperezcarrillo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalesAdapter(private val listaAnimales: List<Animales>) :
    RecyclerView.Adapter<AnimalesAdapter.AnimalesViewHolder>() {

    class AnimalesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.filaTitulo)
        val tipoAlimentacion: TextView = itemView.findViewById(R.id.tipoAlimentacion)
        val tipoReproduccion: TextView = itemView.findViewById(R.id.tipoReproduccion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalesViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_animales, parent, false)
        return AnimalesViewHolder(vista)
    }

    override fun onBindViewHolder(holder: AnimalesViewHolder, position: Int) {
        val animal = listaAnimales[position]
        holder.nombre.text = animal.nombre
        holder.tipoAlimentacion.text = animal.tipoAlimentacion
        holder.tipoReproduccion.text = animal.tipoReproduccion
    }

    override fun getItemCount(): Int = listaAnimales.size
}
