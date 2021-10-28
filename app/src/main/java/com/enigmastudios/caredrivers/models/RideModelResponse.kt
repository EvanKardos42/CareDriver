package com.enigmastudios.caredrivers.models


import com.enigmastudios.caredrivers.RideCollection
import com.google.gson.annotations.SerializedName

data class RideModelResponse(
    @SerializedName("rides")
    val rideModels: List<RideModel>
){
    val rideCollection:RideCollection = RideCollection(rideModels)
}