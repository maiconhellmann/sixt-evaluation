package com.maiconhellmann.sixtcodechallenge.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CarCache(
    @PrimaryKey(autoGenerate = true)
    val uuid: Int=0,

    val carImageUrl: String,
    val color: String,
    val fuelLevel: Double,
    val fuelType: String,
    val group: String,
    val id: String,
    val innerCleanliness: String,
    val latitude: Double,
    val licensePlate: String,
    val longitude: Double,
    val make: String,
    val modelIdentifier: String,
    val modelName: String,
    val name: String,
    val series: String,
    val transmission: String
)