package com.maiconhellmann.sixtcodechallenge.feature.list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maiconhellmann.sixtcodechallenge.R
import com.maiconhellmann.sixtcodechallenge.entity.FuelType
import com.maiconhellmann.sixtcodechallenge.entity.TransmissionType
import com.maiconhellmann.sixtcodechallenge.util.GlideApp
import com.maiconhellmann.sixtcodechallenge.util.extensions.inflate
import com.maiconhellmann.sixtcodechallenge.util.extensions.visible
import kotlinx.android.synthetic.main.item_car.view.*

class CarListAdapter : RecyclerView.Adapter<CarListAdapter.ViewHolder>() {

    var cars: List<CarItemModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_car)) {

        fun bind(model: CarItemModel) = with(itemView) {
            val car = model.car
            textViewCarName.text = context.getString(R.string.car_name, car.make, car.modelName)
            textViewTransmission.text = context.getTransmissionText(car.transmission)
            textViewFuel.text = context.getFuelTypeText(car.fuelType)
            textViewLicensePlate.text = car.licensePlate

            if(model.distance == null) {
                textViewPlace.visible(false)
            } else {
                if(model.isDistanceInMeters()) {
                    textViewPlace.text = context.getString(R.string.distance_meter, model.distanceInMeters())
                } else {
                    textViewPlace.text = context.getString(R.string.distance_km, model.distance)
                }

                textViewPlace.visible(true)
            }

            GlideApp.with(context)
                .load(car.carImageUrl)
                .dontTransform()
                .into(imageViewCar)

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

private fun Context.getTransmissionText(transmissionType: TransmissionType): String {
    return when (transmissionType) {
        TransmissionType.AUTOMATIC -> getString(R.string.automatic)
        TransmissionType.MANUAL -> getString(R.string.manual)
        else -> getString(R.string.unkown)
    }
}
private fun Context.getFuelTypeText(fuelType: FuelType): String {
    return when (fuelType) {
        FuelType.DIESEL -> getString(R.string.diesel)
        FuelType.PETROL -> getString(R.string.petrol)
        else -> getString(R.string.unkown)
    }
}