package com.enigmastudios.caredrivers.network

import com.enigmastudios.caredrivers.models.RideModelResponse
import retrofit2.http.GET

interface CareDriverService {

    @GET("hsd-interview-resources/simplified_my_rides_response.json")
    suspend fun getRides(): RideModelResponse
}