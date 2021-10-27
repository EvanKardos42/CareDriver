package com.enigmastudios.caredrivers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.models.SummeryHeaderModel
import com.enigmastudios.caredrivers.models.ViewType
import com.enigmastudios.caredrivers.utils.UtilsStringRide

class RideAdapter(list: List<ViewType>): RecyclerView.Adapter<RideAdapter.RideDataHolder>() {

    private var  data: List<ViewType> = list

    companion object {
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_TRIP = 2
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideDataHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        if (viewType == R.layout.trip_card_item){
            val tripView = inflater.inflate(R.layout.trip_card_item,parent,false)
            return RideDataHolder.TripHolder(tripView)
        }
        val headerView = inflater.inflate(R.layout.trip_header_item,parent,false)
        return RideDataHolder.HeaderHolder(headerView)
    }

    override fun onBindViewHolder(holder: RideDataHolder, position: Int) {
        val model = data[position]
        when (holder) {
            is RideDataHolder.TripHolder -> holder.bind(model as RideModel)

            is RideDataHolder.HeaderHolder -> holder.bind(model as SummeryHeaderModel)
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is SummeryHeaderModel -> R.layout.trip_header_item
            else -> R.layout.trip_card_item

        }
    }

    sealed class RideDataHolder(itemView: View):RecyclerView.ViewHolder(itemView){
          class TripHolder(itemView: View): RideDataHolder(itemView){
              var addresses:TextView = itemView.findViewById(R.id.addresses_textView)
              var timeFrame:TextView = itemView.findViewById(R.id.timeframe_textView)
              var cost:TextView = itemView.findViewById(R.id.estimation_price_textView)
              var numRiders:TextView = itemView.findViewById(R.id.num_riders_textView)

              fun bind(ride:RideModel){
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
            var date:TextView
            var time:TextView
            var total:TextView
            init{
                total = itemView.findViewById(R.id.Total_textView)
                date = itemView.findViewById(R.id.Date_textView)
                time = itemView.findViewById(R.id.timeWindow_textView)

            }

            fun bind(header:SummeryHeaderModel){
                val timeString = header.startTime +" - "+ header.endTime
                time.text = timeString
                date.text = header.date
                val totalString =  header.total
                total.text = totalString
            }
        }
    }

}