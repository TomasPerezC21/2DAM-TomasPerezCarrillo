package com.dam.notasapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.xml.transform.ErrorListener

class AdaptadorNotas(): RecyclerView.Adapter<ViewHolderNota>() {
    private var notas: MutableList<Nota> = mutableListOf()



    private var onDeleteNota:((Nota)->Unit)?=null

    fun setOnDeleteNotaListener(listener: (Nota)->Unit){
        this.onDeleteNota = listener
    }

    //private lateinit var listener: AdaptadorCallback

//    interface AdaptadorCallback{
//        fun onDeleteNota(nota: Nota)
//    }

//    fun setAdaptadorCallback(listener: AdaptadorCallback){
//        this.listener=listener
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderNota {
        val vista: View = LayoutInflater.from(parent.context).inflate(R.layout.fila_notas, parent, false)
        val viewHolder = ViewHolderNota(vista)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderNota, position: Int) {
        val nota: Nota=this.notas[position]
        holder.textViewTitulo.text=nota.titulo
        holder.textViewDescripcion.text=nota.descripcion
        //
        holder.menuNota.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_borrar -> {
//                    notas.remove(nota)
//                    notifyDataSetChanged()
                  //  listener.onDeleteNota(nota)
                    onDeleteNota?.invoke(nota)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return this.notas.size
    }
    fun addNota(nota:Nota){
        notas.add(nota)
        //notifyDataSetChanged()
        notifyItemInserted(notas.size-1)
    }

    fun setNotas(notas: List<Nota>) {
        this.notas = notas as MutableList<Nota>
        notifyDataSetChanged()
    }

    fun deleteNota(nota: Nota) {
        val pos = notas.indexOf(nota)
        notas.removeAt(pos)
        notifyItemRemoved(pos)

    }

}