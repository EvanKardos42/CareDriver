package com.enigmastudios.caredrivers.ui

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.enigmastudios.caredrivers.models.OrderedWaypoint


class AddressAdapter(private val list: List<OrderedWaypoint>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        return p1!!
    }

}