package com.example.apptodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorRecycler(private val listaPersonas: List<Persona>):
    RecyclerView.Adapter<AdaptadorRecycler.PersonaViewHolder>()
{

        //Esta clase se tiene que llamar igual a lo que pone en la linea10
        class PersonaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val nombre: TextView = itemView.findViewById(R.id.nombre)
            val edad: TextView = itemView.findViewById(R.id.edad)
            val apellidos: TextView = itemView.findViewById(R.id.apellidos)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
            val vista = LayoutInflater.from(parent.context)
                .inflate(R.layout.fila_persona, parent, false)
            return PersonaViewHolder(vista)
        }

        override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
            val persona = listaPersonas[position]
            
            holder.nombre.text = persona.nombre
            holder.edad.text = persona.edad.toString()
            holder.apellidos.text = persona.apellido
        }

    override fun getItemCount(): Int = listaPersonas.size



    }
