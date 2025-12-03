package com.example.ejercicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FragmentRutinas : Fragment() {

    private val listaEjercicios = listOf("Press Banca", "Sentadilla", "Aperturas", "Fondos")

    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rutinas, container, false)

        recycler = view.findViewById(R.id.miRecycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        recycler.adapter = RutinasAdapter(listaEjercicios)


        return view
    }

}