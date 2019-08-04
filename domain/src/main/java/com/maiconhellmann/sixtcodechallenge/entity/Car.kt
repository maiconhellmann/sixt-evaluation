package com.maiconhellmann.sixtcodechallenge.entity

data class Car(
    val carImageUrl: String,
    val color: String,
    val fuelLevel: Double,
    val fuelType: FuelType,
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
    val transmission: TransmissionType
)

enum class FuelType(val value: String) {
    DIESEL("D"), PETROL("P"), UNKNOWN("UNKNOWN");

    companion object {
        fun fromValue(textValue: String): FuelType {
            return values().firstOrNull { it.value == textValue } ?: UNKNOWN
        }
    }
}
enum class TransmissionType(val value: String) {
    MANUAL("M"), AUTOMATIC("A"), UNKNOWN("UNKNOWN");

    companion object {
        fun fromValue(textValue: String): TransmissionType {
            return values().firstOrNull { it.value == textValue } ?: UNKNOWN
        }
    }
}