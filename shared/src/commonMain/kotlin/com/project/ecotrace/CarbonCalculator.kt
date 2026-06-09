package com.project.ecotrace

object CarbonFactors {
    const val PETROL_CAR = 0.17
    const val DIESEL_CAR = 0.171
    const val ELECTRIC_VEHICLE = 0.05
    const val PUBLIC_TRANSIT = 0.04
    const val MOTORBIKE = 0.11
    const val ELECTRICITY_GRID = 0.45
    const val MEAT_HEAVY = 7.2
    const val VEGETARIAN = 3.8
    const val VEGAN = 2.9
}

data class CarbonInput(
    val transportKm: Double = 0.0,
    val transportType: String = "Petrol Car",
    val electricityKwh: Double = 0.0,
    val dietType: String = "Vegetarian"
)

data class CarbonResult(
    val transportEmissions: Double,
    val energyEmissions: Double,
    val dietEmissions: Double,
    val totalEmissions: Double,
    val primaryContributor: String,
    val recommendations: List<String>
)

class CarbonCalculator {
    fun calculate(input: CarbonInput): CarbonResult {
        val transportFactor = when (input.transportType) {
            "Petrol Car" -> CarbonFactors.PETROL_CAR
            "Diesel Car" -> CarbonFactors.DIESEL_CAR
            "Electric Vehicle" -> CarbonFactors.ELECTRIC_VEHICLE
            "Public Transit" -> CarbonFactors.PUBLIC_TRANSIT
            "Motorbike" -> CarbonFactors.MOTORBIKE
            else -> 0.0
        }
        val transportScore = input.transportKm * transportFactor
        val energyScore = input.electricityKwh * CarbonFactors.ELECTRICITY_GRID
        val dietScore = when (input.dietType) {
            "Meat Heavy" -> CarbonFactors.MEAT_HEAVY
            "Vegetarian" -> CarbonFactors.VEGETARIAN
            "Vegan" -> CarbonFactors.VEGAN
            else -> 4.0
        }

        val total = transportScore + energyScore + dietScore
        val breakdown = mapOf(
            "Transportation" to transportScore,
            "Household Energy" to energyScore,
            "Dietary Habits" to dietScore
        )
        val primary = breakdown.maxByOrNull { it.value }?.key ?: "Unknown"

        val tips = mutableListOf<String>()
        when (primary) {
            "Transportation" -> {
                tips.add("Consider carpooling or using public transit 2 days a week.")
                if (input.transportType != "Electric Vehicle") {
                    tips.add("Your next vehicle switch could be an EV to lower transport footprint by up to 70%.")
                }
            }
            "Household Energy" -> {
                tips.add("Unplug phantom electronics and switch to energy-efficient LED bulbs.")
                tips.add("Try optimizing AC or heating usage by adjusting the thermostat by 1-2 degrees.")
            }
            "Dietary Habits" -> {
                if (input.dietType == "Meat Heavy") {
                    tips.add("Try introducing a 'Meatless Monday' into your routine to reduce dietary impact.")
                }
                tips.add("Sourcing locally grown produce can sharply cut down on 'food miles' emissions.")
            }
        }
        tips.add("Planting trees or supporting verified carbon offset initiatives can mitigate your structural footprint.")

        return CarbonResult(
            transportEmissions = transportScore,
            energyEmissions = energyScore,
            dietEmissions = dietScore,
            totalEmissions = total,
            primaryContributor = primary,
            recommendations = tips
        )
    }
}