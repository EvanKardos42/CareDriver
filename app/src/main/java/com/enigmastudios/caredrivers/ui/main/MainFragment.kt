package com.enigmastudios.caredrivers.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.models.RideModelResponse

class MainFragment : Fragment() {
    private val TAG ="EVANKARDOS_MAINFRAGMENT"
    private lateinit var response: RideModelResponse
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.getRides().observe(viewLifecycleOwner) {
            response = it
            Log.d(TAG, response.rideModels.size.toString())
        }
    }

}