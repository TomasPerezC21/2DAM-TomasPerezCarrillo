package com.example.ejercicio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ImagesAdapter(private val lista: List<Imagenes>) : RecyclerView.Adapter<ImagesAdapter.MiViewHolder>() {

    // A. VIEWHOLDER
    class MiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imageView3)
    }

    // B. ON CREATE (Inflar diseño de fila)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_imagen, parent, false)
        return MiViewHolder(view)
    }

    // C. ON BIND (Poner datos)
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val imagen = lista[position]
        holder.imagen.setImageResource(imagen.imagenResId)


    }

    // D. GET ITEM COUNT
    override fun getItemCount() = lista.size
}
