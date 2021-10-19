package com.enigmastudios.caredrivers.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.enigmastudios.caredrivers.models.RideModelResponse
import com.enigmastudios.caredrivers.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    val fetcher = RetrofitBuilder.apiService

    fun getRides() = liveData<RideModelResponse>(Dispatchers.IO) {
        emit(fetcher.getRides())
    }
}