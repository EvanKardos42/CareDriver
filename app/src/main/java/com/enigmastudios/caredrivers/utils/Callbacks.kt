package com.enigmastudios.caredrivers.utils

import com.enigmastudios.caredrivers.models.RideModel

interface Callbacks {
    fun onRideSelected(ride: RideModel?)

}