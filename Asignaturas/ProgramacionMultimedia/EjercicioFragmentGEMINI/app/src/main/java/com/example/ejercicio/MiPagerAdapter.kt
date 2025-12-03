package com.example.ejercicio

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MiPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3 // Número total de pestañas

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentInfo() // Tu primer fragment
            1 -> FragmentRutinas() // Tu segundo fragment
            2 -> FragmentImages()
            else -> FragmentInfo()
        }
    }





}