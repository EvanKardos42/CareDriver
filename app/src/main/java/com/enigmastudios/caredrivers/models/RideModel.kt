package com.enigmastudios.caredrivers.models


import com.enigmastudios.caredrivers.ui.main.RideAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RideModel(
    override val viewType: Int = RideAdapter.VIEW_TYPE_TRIP,
    @SerializedName("ends_at")
    val endsAt: String,
    @SerializedName("estimated_earnings_cents")
    val estimatedEarningsCents: Int,
    @SerializedName("estimated_ride_miles")
    val estimatedRideMiles: Double,
    @SerializedName("estimated_ride_minutes")
    val estimatedRideMinutes: Int,
    @SerializedName("in_series")
    val inSeries: Boolean,
    @SerializedName("ordered_waypoints")
    val orderedWaypoints: List<OrderedWaypoint>,
    @SerializedName("starts_at")
    val startsAt: String,
    @SerializedName("trip_id")
    val tripId: Int
):ViewType,Serializable