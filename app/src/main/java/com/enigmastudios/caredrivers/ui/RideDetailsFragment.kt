package com.enigmastudios.caredrivers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.databinding.FragmentRideDetailsBinding
import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.utils.UtilsStringRide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions


private const val ARG_RIDE_DATA = "crime_Data"
private const val MAP_BUNDLE_KEY = "MAP_KEY"
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
        val appCompatActivity = activity as AppCompatActivity
        val appBar = appCompatActivity.supportActionBar
        appBar?.setTitle(R.string.frag_detail_title)

        ride = arguments?.getSerializable(ARG_RIDE_DATA) as RideModel
        bind = FragmentRideDetailsBinding.inflate(inflater,container,false)


        attachModelToTextViews()
        attachList()
        attachMap(savedInstanceState)
        return bind.root
    }


    override fun onStart() {
        super.onStart()
        bind.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        bind.mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        bind.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        bind.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        bind.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        bind.mapView.onLowMemory()
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

    private fun attachMap(savedInstanceState: Bundle?){
        var mapViewbundle:Bundle? = null
        if (savedInstanceState != null){
            mapViewbundle = savedInstanceState.getBundle(MAP_BUNDLE_KEY)
        }

        bind.mapView.onCreate(mapViewbundle)
        bind.mapView.getMapAsync {googleMap ->
            val mlatlngbounds = LatLngBounds.builder()

            for (wayPoint in ride.orderedWaypoints) {
                val p = LatLng(wayPoint.location.lat, wayPoint.location.lng)
                mlatlngbounds.include(p)
                googleMap.addMarker(MarkerOptions().position(p).title("Marker"))
            }
            googleMap.setOnMapLoadedCallback {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mlatlngbounds.build(), 50))

            }

        }
    }

    private fun attachList(){
        bind.addressListView.adapter = context?.let {
            AddressAdapter( ride.orderedWaypoints, it)
        }
    }

}