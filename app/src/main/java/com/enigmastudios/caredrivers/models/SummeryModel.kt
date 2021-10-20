package com.enigmastudios.caredrivers.models

import com.enigmastudios.caredrivers.ui.TripAdapter
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class SummeryModel(
    val viewType:Int = TripAdapter.VIEW_TYPE_HEADER,
    val date: LocalDate,
    val startTime:LocalTime,
    val endTime:LocalTime,
    val total:Float
)
