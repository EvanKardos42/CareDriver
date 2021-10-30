package com.enigmastudios.caredrivers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enigmastudios.caredrivers.models.RideModel
import com.enigmastudios.caredrivers.ui.RideDetailsFragment
import com.enigmastudios.caredrivers.ui.main.MainFragment
import com.enigmastudios.caredrivers.utils.Callbacks

class MainActivity : AppCompatActivity(), Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setTitle(R.string.main_view_title)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onRideSelected(ride: RideModel) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, RideDetailsFragment.newInstance(ride))
            .addToBackStack(null)
            .commit()
    }

}