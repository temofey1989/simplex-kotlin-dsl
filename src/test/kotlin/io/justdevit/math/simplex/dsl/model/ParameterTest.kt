package io.justdevit.math.simplex.dsl.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ParameterTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `Should reject negative or zero index`(index: Int) {
        assertThrows<IllegalArgumentException> { Parameter(coefficient = 1.0, index = index) }
    }
}
