package com.enigmastudios.caredrivers.models

import com.enigmastudios.caredrivers.ui.TripAdapter
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class SummeryModel(
    val date: String,
    val startTime:String,
    val endTime:String,
    val total:Float,
    override val viewType: Int = TripAdapter.VIEW_TYPE_HEADER
):ViewType
