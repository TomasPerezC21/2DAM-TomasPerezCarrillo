package com.example.ejercicio

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    private lateinit var adaptador: MiPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Configuración de la toolbar

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.miToolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "App de Tomás"

        //Bindeo

        viewPager = findViewById(R.id.miViewPager2)
        tabLayout = findViewById(R.id.tabs)

        adaptador = MiPagerAdapter(this)
        viewPager.adapter = adaptador

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "INFO"
                1 -> tab.text = "RUTINAS"
                2 -> tab.text = "IMAGENES"
            }
        }.attach()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, Activity1::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}