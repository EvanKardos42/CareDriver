package com.enigmastudios.caredrivers.utils

import com.enigmastudios.caredrivers.models.OrderedWaypoint
import java.text.SimpleDateFormat
import java.util.*

class UtilsStringRide {
    companion object {
        private val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        fun dateToString(utcString: String):String {

            val value = utcFormat.parse(utcString)
            val dateFormat = SimpleDateFormat("E M/d", Locale.getDefault())
            return dateFormat.format(value!!).toString()
        }

        fun timeToString(utcString: String):String {
            utcFormat.timeZone = TimeZone.getTimeZone("UTC")
            val value = utcFormat.parse(utcString)
            val dateFormat = SimpleDateFormat("KK:mma", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(value!!).toString().removeSuffix("M").lowercase()
        }

        fun locationsToString(wayPoints:List<OrderedWaypoint>):String{
            var addressesString = ""
            val size = wayPoints.size-1
            wayPoints.forEachIndexed{ idx, value ->
                val position = (idx+1).toString()
                val address = value.location.address

                addressesString += if(idx == size) "$position. $address"
                                    else "$position. $address\n"

            }
            return addressesString
        }

        fun passengersToString(waypoints: List<OrderedWaypoint>):String{
            var counterBooster = 0
            var countPassenger = 0
            waypoints.forEach {
                it.passengers.forEach {
                    if (it.boosterSeat) {
                        counterBooster += 1
                    }
                    countPassenger+=1
                }
            }
            if (counterBooster == 0){
                return "($countPassenger Riders)"
            }
            return "($countPassenger Riders * $counterBooster booster)"
        }
    }
}