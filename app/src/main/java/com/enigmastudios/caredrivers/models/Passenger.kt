package com.enigmastudios.caredrivers.models


import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("booster_seat")
    val boosterSeat: Boolean,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int
)