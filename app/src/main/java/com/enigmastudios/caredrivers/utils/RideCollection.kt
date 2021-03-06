package com.enigmastudios.caredrivers.utils

import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.models.SummeryHeaderModel
import com.enigmastudios.caredrivers.models.ViewType
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

class RideCollection(listOfRides : List<RideModel>) {
    private val map = LinkedHashMap<String,ArrayList<RideModel>>()

    init{
        for(ride in listOfRides){
            val date = UtilsStringRide.dateToString(ride.startsAt)
            if(map.containsKey(date)){
                map[date]?.add(ride)
            }else{
                map[date] = ArrayList()
                map[date]?.add(ride)
            }
        }
    }

    fun toListWithHeaders():List<ViewType>{
        val values = ArrayList<ViewType>()
        for(key in map.keys){
            var sum = 0.0F
            val listOfRides = map[key]!!
            for(ride in map[key]!!){
                sum += ride.estimatedEarningsCents
            }
            sum /= 100
            val sumString = String.format("$%.2f",sum)
            val startTime = UtilsStringRide.timeToString(listOfRides[0].startsAt)
            val endTime = UtilsStringRide.timeToString(listOfRides[listOfRides.size-1].endsAt)
            val header = SummeryHeaderModel( date = key,
                startTime= startTime,
                endTime = endTime ,
                total = sumString)
            values.add(header)
            values.addAll(listOfRides)
        }
        return values
    }
}