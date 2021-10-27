package com.enigmastudios.caredrivers.models

import com.enigmastudios.caredrivers.ui.RideAdapter

data class SummeryHeaderModel(
    val date: String,
    val startTime:String,
    val endTime:String,
    val total:String,
    override val viewType: Int = RideAdapter.VIEW_TYPE_HEADER
):ViewType
