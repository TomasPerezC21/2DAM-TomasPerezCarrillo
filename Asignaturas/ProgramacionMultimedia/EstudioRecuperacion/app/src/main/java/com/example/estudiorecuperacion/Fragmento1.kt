package com.example.estudiorecuperacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Fragmento1 : Fragment() {


    //datos

    private val listaNombres = listOf(
        "Tom", "Borja", "Jose Juan", "Pepe"
    )

    private lateinit var RecyclerView: RecyclerView

    private lateinit var adaptador: NombresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_fragmento1, container, false)

        RecyclerView = view.findViewById(R.id.recycler1)
        RecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adaptador = NombresAdapter(listaNombres)

        RecyclerView.adapter = adaptador
        return view
    }


}