package com.onethefull.kotlin_coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/24 10:21 오전
 * @desc
 */
class NetworkService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val sunflowService = retrofit.create(SunflowService::class.java)

    suspend fun allPlants(): List<Plant> = withContext(Dispatchers.Default){
        val result = sunflowService.getAllPlants()
        result.shuffled()
    }

    suspend fun plantsByGrowZone(growZone: GrowZone) = withContext(Dispatchers.Default){
        val result = sunflowService.getAllPlants()
        result.filter { it.growZoneNumber == growZone.number }.shuffled()
    }

    suspend fun customPlantSortOrder(): List<String> = withContext(Dispatchers.Default){
        val result = sunflowService.getCustomPlantSortOrder()
        result.map { plant -> plant.plantId }
    }
}

interface SunflowService{
    @GET("googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
    suspend fun getAllPlants(): List<Plant>

    @GET("googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/custom_plant_sort_order.json")
    suspend fun getCustomPlantSortOrder(): List<Plant>
}