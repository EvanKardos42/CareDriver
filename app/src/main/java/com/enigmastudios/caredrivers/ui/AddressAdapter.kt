package com.enigmastudios.caredrivers.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.enigmastudios.caredrivers.R
import com.enigmastudios.caredrivers.R.layout.pickup_dropoff_point_item
import com.enigmastudios.caredrivers.models.OrderedWaypoint


class AddressAdapter(private val list: List<OrderedWaypoint>, context:Context): BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

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
        val view: View?
        val vh: Holder
        if (p1 == null) {
            view = this.mInflater.inflate(R.layout.pickup_dropoff_point_item, p2, false)
            vh = Holder(view)
            view.tag = vh
        } else {
            view = p1
            vh = view.tag as Holder
        }

        vh.bind(list[p0])
        return view!!
    }
    private class Holder(view:View){
        private val address = view.findViewById<TextView>(R.id.pickup_address_textview)
        private val anchor = view.findViewById<TextView>(R.id.anchor_textView)
        private val image = view.findViewById<ImageView>(R.id.image_address)

        fun bind(model: OrderedWaypoint){
            address.text = model.location.address

            if(model.anchor){
                anchor.setText(R.string.pickup)
                image.setImageResource(R.mipmap.diamond)
            }
            else{
                anchor.setText( R.string.dropoff)
                image.setImageResource(R.mipmap.circle)

            }

        }
    }

}