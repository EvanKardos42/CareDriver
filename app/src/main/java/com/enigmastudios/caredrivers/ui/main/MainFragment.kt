package com.enigmastudios.caredrivers.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.RideCollection
import com.enigmastudios.caredrivers.models.RideModelResponse

class MainFragment : Fragment() {
    private val TAG ="EVANKARDOS_MAINFRAGMENT"
    private lateinit var response: RideCollection
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getRides().observe(viewLifecycleOwner){
            response = RideCollection(it.rideModels)
            for(item in response.toList()){
                Log.d("EVAN KARDOS", item.toString())
            }
        }
    }


}