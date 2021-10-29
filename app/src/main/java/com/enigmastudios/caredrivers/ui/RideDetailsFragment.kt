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
import com.google.android.gms.maps.SupportMapFragment
import okhttp3.internal.Util
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


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
        attachList()
        attachMap()
        return bind.root
    }



    private fun attachModelToTextViews(){
        val total = ride.estimatedEarningsCents/100.0
        val startTime = UtilsStringRide.timeToString(ride.startsAt)
        val endTime = UtilsStringRide.timeToString(ride.endsAt)
        val timeWindow = "$startTime - $endTime"
        val tripIDString = "Trip ID: ${ride.tripId} * " +
                "${ride.estimatedRideMiles} * " +
                "${ride.estimatedRideMinutes} min"

        bind.DateTextView.text = UtilsStringRide.dateToString(ride.startsAt)
        bind.timeWindowTextView.text = timeWindow
        bind.tripIDTextView.text = tripIDString
        bind.TotalTextView.text = String.format("$%.2f",total)
        if(!ride.inSeries){
            bind.seriesTextView.visibility = View.GONE
        }
    }

   private fun attachMap(){
       val mapFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.mapView) as? SupportMapFragment

       mapFragment!!.getMapAsync {
            val places = mutableListOf<LatLng>()
            for(point in ride.orderedWaypoints) {
                places.add(LatLng(point.location.lat, point.location.lng))
            }
            for(p in places) {
                it.addMarker(MarkerOptions().position(p))
            }

        }
    }

    private fun attachList(){
        bind.addressListView.adapter = context?.let {
            AddressAdapter( ride.orderedWaypoints, it)
        }
    }

}