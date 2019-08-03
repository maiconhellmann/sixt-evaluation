package com.maiconhellmann.sixtcodechallenge.feature.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maiconhellmann.sixtcodechallenge.R
import com.maiconhellmann.sixtcodechallenge.entity.Car
import com.maiconhellmann.sixtcodechallenge.util.extensions.inflate

class CarListAdapter : RecyclerView.Adapter<CarListAdapter.ViewHolder>() {

    var cars: List<Car> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_car)) {

        fun bind(car: Car) = with(itemView) {
            //TODO bind car

            itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int = cars.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cars[position])
    }
}