package com.enigmastudios.caredrivers.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.utils.RideCollection
import com.enigmastudios.caredrivers.ui.RideViewModel
import com.enigmastudios.caredrivers.utils.Callbacks

private val TAG ="EVANKARDOS_MAINFRAGMENT"

class MainFragment : Fragment() {

    private lateinit var viewModel: RideViewModel
    private lateinit var recyclerView: RecyclerView
    private var callbacks: Callbacks? = null
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(this).get(RideViewModel::class.java)
        recyclerView  = view.findViewById(R.id.list_of_rides_recycleView)
        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.response.observe(viewLifecycleOwner){
            val collect = RideCollection(it.rideModels)
            recyclerView.adapter = RideAdapter(collect.toListWithHeaders(),callbacks)
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

}