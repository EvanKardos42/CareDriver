package com.enigmastudios.caredrivers

import android.util.Log
import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.models.SummeryModel
import com.enigmastudios.caredrivers.models.ViewType
import com.enigmastudios.caredrivers.utils.UtilsDate
import java.util.*
import kotlin.collections.ArrayList

class RideCollection(listOfRides : List<RideModel>) {
    val map = TreeMap<String,ArrayList<RideModel>>()

    init{
        for(ride in listOfRides){
            val date = UtilsDate.getDateFromString(ride.startsAt)
            if(map.containsKey(date)){
                map[date]?.add(ride)
            }else{
                map[date] = ArrayList()
                map[date]?.add(ride)
            }
        }
    }

    fun toList():List<ViewType>{
        val values = ArrayList<ViewType>()
        for(key in map.keys){
            var sum:Float = 0.0F
            var listOfRides = map[key]!!
            for(ride in map[key]!!){
                sum += ride.estimatedEarningsCents
            }
            sum /= 100
            val startTime = UtilsDate.getTimeFromString(listOfRides[0].startsAt)
            val endTime = UtilsDate.getTimeFromString(listOfRides[listOfRides.size-1].endsAt)
            val header = SummeryModel( date = key,startTime= startTime,endTime = endTime ,total = sum)
            values.add(header)
            values.addAll(listOfRides)
        }
        return values
    }
}