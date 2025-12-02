package com.dam.apptiempo


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dam.apptiempo.adapters.SearchCityAdapter
import com.dam.apptiempo.api.RetrofitClient
import com.dam.apptiempo.databinding.ActivitySearchCityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.toString


class SearchCityActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySearchCityBinding
    private lateinit var adapterSearchCity: SearchCityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySearchCityBinding.inflate(layoutInflater)


        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        adapterSearchCity = SearchCityAdapter()
        binding.rvSearch.adapter = adapterSearchCity
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        Log.d("searchcity", binding.searchView.id.toString())
//        val buscador = findViewById<SearchView>(R.id.searchview)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("searchcity", query.toString())
                if (!query.isNullOrEmpty() && query.length > 2) {
                    search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        adapterSearchCity.setOnClickListener(object:SearchCityAdapter.CityAdapterCallback{
            override fun citySelected(city: City) {
                val intent = Intent()
                intent.putExtra("select_name", city.name)
                intent.putExtra("select_country", city.country)
                intent.putExtra("select_latitude", city.latitude)
                intent.putExtra("select_longitude", city.longitude)
                setResult(RESULT_OK, intent)
                finish()
            }

        })



    }


    private fun search(query: String) {
        Log.d("searchcity", "llamada a search")


        lifecycleScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.api.searchCities(query)
            val results = response.body()?.results ?: emptyList()
            Log.d("searchcity", response.body().toString())


            withContext(Dispatchers.Main) {
                adapterSearchCity.setCities(results)
                adapterSearchCity.notifyDataSetChanged()
            }


        }


    }
}



