package com.example.ejercicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentImages : Fragment() {

    private val misDatos = listOf(
        Imagenes("Foto Paisaje", R.drawable.outline_assist_walker_24),
        Imagenes("Foto Perro", R.drawable.outline_accessibility_new_24),
        Imagenes("Foto Gato", R.drawable.outline_account_child_invert_24)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_images, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerImages)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = ImagesAdapter(misDatos)

        return view
    }


}