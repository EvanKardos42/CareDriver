package com.enigmastudios.caredrivers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.enigmastudios.caredrivers.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers

class RideViewModel : ViewModel() {
    private val fetcher = RetrofitBuilder.apiService

    val response = liveData(Dispatchers.IO) {
        emit(fetcher.getRides())
    }
}