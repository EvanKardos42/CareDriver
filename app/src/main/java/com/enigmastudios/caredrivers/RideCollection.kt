package com.enigmastudios.caredrivers

import android.os.Build
import com.enigmastudios.caredrivers.models.RideModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class RideCollection(listOfRides : List<RideModel>) {
    val map = TreeMap<LocalDate,List<RideModel>>()
    fun sortByDate(){
        val time = SimpleDateFormat("")
        /*val dateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.ofInstant(,ZoneId.systemDefault())
        } else {
            TODO("VERSION.SDK_INT < O")
        }*/


    }
}