package com.enigmastudios.caredrivers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.models.RideModelResponse
import com.enigmastudios.caredrivers.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}