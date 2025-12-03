package com.example.estudiorecuperacion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2

    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Bindeo

        viewPager2=findViewById(R.id.viewPager2)
        tabLayout= findViewById(R.id.tabla)

        val adaptador = ViewPagerAdapter(this)
        viewPager2.adapter = adaptador

        TabLayoutMediator(tabLayout, viewPager2){
            tab, position ->
                tab.text =  when (position){
                        0-> "fragmento1"
                        1-> "fragmento2"
                        //2-> "fragmento3"
                    else -> ""
                }
        }.attach()


    }
}