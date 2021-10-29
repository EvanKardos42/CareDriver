package com.enigmastudios.caredrivers.ui

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.databinding.FragmentRideDetailsBinding
import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.utils.UtilsStringRide
import okhttp3.internal.Util

private const val ARG_RIDE_DATA = "crime_Data"
class RideDetailsFragment:Fragment(){
    companion object {
        fun newInstance(crimeId: RideModel): RideDetailsFragment {
            val args = Bundle().apply {
                putSerializable(ARG_RIDE_DATA, crimeId)
            }
            return RideDetailsFragment().apply {
                arguments = args
            }
        }
    }

    private  lateinit var ride:RideModel
    private lateinit var bind:FragmentRideDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ride = arguments?.getSerializable(ARG_RIDE_DATA) as RideModel
        bind = FragmentRideDetailsBinding.inflate(inflater,container,false)
        attachModelToTextViews()
        attachMap()
        return bind.root
    }



    private fun attachModelToTextViews(){
        bind.DateTextView.text = UtilsStringRide.dateToString(ride.startsAt)

        val startTime = UtilsStringRide.timeToString(ride.startsAt)
        val endTime = UtilsStringRide.timeToString(ride.endsAt)
        bind.timeWindowTextView.text = "$startTime - $endTime"

        val tripIDString = "${ride.tripId} * " +
                "${ride.estimatedRideMiles} * " +
                "${ride.estimatedRideMinutes} min"
        bind.tripIDTextView.text = tripIDString
        val total = ride.estimatedEarningsCents/100.0
        bind.TotalTextView.text = String.format("$%.2f",total)
        if(!ride.inSeries){
            bind.seriesTextView.visibility = View.GONE
        }
    }

   private fun attachMap(){
        bind.mapView
    }

    private fun attachList(){

    }

}