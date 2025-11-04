package com.dam.repasoexamen

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (activity: AppCompatActivity, var itemsCount: Int) :
    FragmentStateAdapter(activity) {
    private val howToPMDM= PMDMFragment()
    private val otroFragment = OtroFragment()

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0-> howToPMDM
            1-> otroFragment
            else -> PMDMFragment()
        }
    }
}
