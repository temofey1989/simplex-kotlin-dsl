package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.Parameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ParameterTest {

    @Nested
    inner class NegateTests {

        @Test
        fun `Should be able to negate parameter`() {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = parameter.negate()

            with(result) {
                assertThat(coefficient).isEqualTo(-parameter.coefficient)
                assertThat(index).isEqualTo(parameter.index)
            }
        }
    }

    @Nested
    inner class PlusParameterTests {

        @Test
        fun `Should be able to create linear expression builder on plus operation`() {
            val p1 = Parameter(coefficient = 10.0, index = 1)
            val p2 = Parameter(coefficient = 20.0, index = 2)

            val result = (p1 + p2).build()

            with(result) {
                assertThat(coefficients).containsExactly(p1.coefficient, p2.coefficient)
                assertThat(constant).isEqualTo(0.0)
            }
        }
    }

    @Nested
    inner class PlusMinusNumberTests {

        @Test
        fun `Should be able to create linear expression builder on plus operation`() {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = (parameter + 20).build()

            with(result) {
                assertThat(coefficients).containsExactly(parameter.coefficient)
                assertThat(constant).isEqualTo(20.0)
            }
        }

        @Test
        fun `Should be able to create linear expression builder on minus operation`() {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = (parameter - 20).build()

            with(result) {
                assertThat(coefficients).containsExactly(parameter.coefficient)
                assertThat(constant).isEqualTo(-20.0)
            }
        }
    }
}
