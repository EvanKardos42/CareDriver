package com.enigmastudios.caredrivers.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.models.SummeryHeaderModel
import com.enigmastudios.caredrivers.utils.UtilsStringRide


open class RideDataHolder(itemView: View): RecyclerView.ViewHolder(itemView){

     class TripHolder(itemView: View,itemClicked: (Int) -> Unit): RideDataHolder(itemView) {
        private var addresses: TextView = itemView.findViewById(R.id.addresses_textView)
        private var timeFrame: TextView = itemView.findViewById(R.id.timeframe_textView)
        private var cost: TextView = itemView.findViewById(R.id.estimation_price_textView)
        private var numRiders: TextView = itemView.findViewById(R.id.num_riders_textView)
        init {

            itemView.setOnClickListener{
                itemClicked(adapterPosition)
            }
        }
        fun bind(ride: RideModel){
            val startTime =  UtilsStringRide.timeToString(ride.startsAt)
            val endTime  = UtilsStringRide.timeToString(ride.startsAt)
            val timeString = "$startTime - $endTime"
            val costsString:Double = (ride.estimatedEarningsCents/100.0)

            cost.text = String.format("$%.2f",costsString)
            timeFrame.text = timeString
            addresses.text = UtilsStringRide.locationsToString(ride.orderedWaypoints)
            numRiders.text = UtilsStringRide.passengersToString(ride.orderedWaypoints)
        }

    }

    class HeaderHolder( itemView: View): RideDataHolder(itemView){
        var date: TextView = itemView.findViewById(R.id.Date_textView)
        var time: TextView = itemView.findViewById(R.id.timeWindow_textView)
        var total: TextView = itemView.findViewById(R.id.Total_textView)

        fun bind(header: SummeryHeaderModel){
            val timeString = header.startTime +" - "+ header.endTime
            time.text = timeString
            date.text = header.date
            val totalString =  header.total
            total.text = totalString
        }
    }
}
