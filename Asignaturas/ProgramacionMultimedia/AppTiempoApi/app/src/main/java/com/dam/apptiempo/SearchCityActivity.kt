package com.dam.apptiempo


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.apptiempo.adapters.SearchCityAdapter
import com.dam.apptiempo.databinding.ActivitySearchCityBinding


class SearchCityActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchCityBinding
    private lateinit var adapterSearchCity: SearchCityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySearchCityBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(R.layout.activity_search_city)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapterSearchCity= SearchCityAdapter()
        binding.rvSearch.adapter=adapterSearchCity
        binding.rvSearch.layoutManager= LinearLayoutManager(this)

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })



    }
}



