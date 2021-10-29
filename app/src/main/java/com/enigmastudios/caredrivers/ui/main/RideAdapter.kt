package com.enigmastudios.caredrivers.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.models.SummeryHeaderModel
import com.enigmastudios.caredrivers.models.ViewType
import com.enigmastudios.caredrivers.utils.Callbacks

class RideAdapter(list: List<ViewType>, private var callbacks: Callbacks?):

    RecyclerView.Adapter<RideDataHolder>() {
    private var data: List<ViewType> = list

    companion object {
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_TRIP = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideDataHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        if (viewType == R.layout.trip_card_item){
            val tripView = inflater.inflate(R.layout.trip_card_item,parent,false)
            return RideDataHolder.TripHolder(tripView) { position ->
                callbacks?.onRideSelected(data[position] as RideModel)
            }
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

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = when(data[position]){
            is SummeryHeaderModel -> R.layout.trip_header_item
            else -> R.layout.trip_card_item
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        callbacks = null
    }
}