package com.project.ecotrace

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CarbonCalculatorTest {

    @Test
    fun testCalculatorEngineReturnsValidMath() {
        val calculator = CarbonCalculator()
        val mockInput = CarbonInput(
            transportKm = 10.0,
            transportType = "Petrol Car",
            electricityKwh = 10.0,
            dietType = "Vegan"
        )
        val result = calculator.calculate(mockInput)
        assertEquals(9.1, result.totalEmissions, 0.01)
        assertTrue(result.recommendations.isNotEmpty())
    }

    @Test
    fun testHighEmissionInputsReflectCorrectPrimaryContributor() {
        val calculator = CarbonCalculator()
        // Force massive electricity consumption to verify contributor flag shifts accurately
        val mockInput = CarbonInput(
            transportKm = 2.0,
            transportType = "Electric Vehicle",
            electricityKwh = 150.0,
            dietType = "Vegan"
        )
        val result = calculator.calculate(mockInput)
        assertEquals("Household Energy", result.primaryContributor)
    }

    @Test
    fun testZeroInputsCalculateCleanBaseline() {
        val calculator = CarbonCalculator()
        // Evaluate zeroed activity levels to ensure math boundaries don't return negative values or errors
        val mockInput = CarbonInput(
            transportKm = 0.0,
            transportType = "Public Transit",
            electricityKwh = 0.0,
            dietType = "Vegan"
        )
        val result = calculator.calculate(mockInput)
        assertTrue(result.totalEmissions >= 0.0, "Emissions should never drop below baseline zero constraints")
    }
}