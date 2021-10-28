package com.enigmastudios.caredrivers.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.enigmastudios.caredrivers.RideCollection
import com.enigmastudios.caredrivers.models.RideModelResponse
import com.enigmastudios.caredrivers.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val fetcher = RetrofitBuilder.apiService

    val response = liveData(Dispatchers.IO) {
        emit(fetcher.getRides())
    }
}