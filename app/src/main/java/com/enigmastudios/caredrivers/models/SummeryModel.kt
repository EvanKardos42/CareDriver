package com.enigmastudios.caredrivers.models

import com.enigmastudios.caredrivers.ui.TripAdapter
import java.util.*

data class SummeryModel(
    val viewType:Int = TripAdapter.VIEW_TYPE_HEADER,
    val date: Date,
    val time:Date,
    val total:Float
)
