package com.enigmastudios.caredrivers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.models.SummeryHeaderModel
import com.enigmastudios.caredrivers.models.ViewType
import com.enigmastudios.caredrivers.utils.UtilsDate
import org.w3c.dom.Text

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
            var addresses:TextView
            var timeFrame:TextView
            var cost:TextView
            var numRiders:TextView

            init{
                addresses = itemView.findViewById(R.id.addresses_textView)
                cost = itemView.findViewById(R.id.estimation_price_textView)
                timeFrame = itemView.findViewById(R.id.timeframe_textView)
                numRiders =  itemView.findViewById(R.id.num_riders_textView)

            }
            fun bind(ride:RideModel){
                val startTime =  UtilsDate.getTimeFromString(ride.startsAt)
                val endTime  = "-" + UtilsDate.getTimeFromString(ride.startsAt)
                val timeString = "$startTime - $endTime"
                var addressesString = ""
                cost.text = (ride.estimatedEarningsCents/100).toString()
                timeFrame.text = timeString

                ride.orderedWaypoints.forEachIndexed{ idx, value ->
                    val position = (idx+1).toString()
                    val address = value.location.address
                    addressesString += "$position. $address\n"
                }
                addresses.text = addressesString
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
                val totalString = "$" + header.total.toString()
                total.text = totalString
            }
        }
    }

}