package com.example.estudiorecuperacion

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2 // Cuántos fragments hay
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Fragmento1()
            else -> Fragmento2()
        }
    }
}