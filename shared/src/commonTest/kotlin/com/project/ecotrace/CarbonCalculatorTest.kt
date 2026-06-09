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

        // Expected: (10 * 0.17) + (10 * 0.45) + 2.9 = 1.7 + 4.5 + 2.9 = 9.1
        assertEquals(9.1, result.totalEmissions, 0.01)
        assertTrue(result.recommendations.isNotEmpty())
    }
}