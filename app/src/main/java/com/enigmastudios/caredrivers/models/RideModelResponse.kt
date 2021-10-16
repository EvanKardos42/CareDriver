package com.enigmastudios.caredrivers.models


import com.google.gson.annotations.SerializedName

data class RideModelResponse(
    @SerializedName("rides")
    val rideModels: List<RideModel>
)