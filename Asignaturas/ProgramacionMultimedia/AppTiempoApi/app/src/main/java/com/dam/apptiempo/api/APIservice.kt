package com.dam.apptiempo.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIservice {
    @GET("/v1/search")
    suspend fun searchCities(@Query("name") name: String): Response<CitiesResponse>
}



