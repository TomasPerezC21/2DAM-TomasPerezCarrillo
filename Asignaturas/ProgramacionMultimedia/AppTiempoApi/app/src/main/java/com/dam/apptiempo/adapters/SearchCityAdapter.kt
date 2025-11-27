package com.dam.apptiempo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.apptiempo.City
import com.dam.apptiempo.R

class SearchCityAdapter: RecyclerView.Adapter<CityViewHolder>() {

    private var arrayCities = mutableListOf<City>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_city_result, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayCities.size
    }

    override fun onBindViewHolder(
        holder: CityViewHolder,
        position: Int
    ) {
        val city = arrayCities[position]
        holder.txtName.text=city.name

    }


}