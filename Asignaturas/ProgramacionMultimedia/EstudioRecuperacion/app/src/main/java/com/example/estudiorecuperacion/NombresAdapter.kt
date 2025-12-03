package com.example.estudiorecuperacion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NombresAdapter(private val nombres: List<String>) : RecyclerView.Adapter<NombresAdapter.NombreViewHolder>() {

    // ViewHolder
    class NombreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NombreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return NombreViewHolder(view)
    }

    override fun onBindViewHolder(holder: NombreViewHolder, position: Int) {
        val nombre = nombres[position]
        holder.tvNombre.text = nombre
    }

    override fun getItemCount(): Int = nombres.size
}