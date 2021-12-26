package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.Parameter
import io.justdevit.math.simplex.dsl.x4
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class NumberTest {

    @Nested
    inner class XTests {

        @Test
        fun `Should be able to create parameter`() {
            val result = 10.x(index = 1)

            with(result) {
                assertThat(coefficient).isEqualTo(10.0)
                assertThat(index).isEqualTo(1)
            }
        }
    }

    @Nested
    inner class TimesTests {

        @Test
        fun `Should be able to create parameter for times operation`() {
            val result = 10 * x4

            with(result) {
                assertThat(coefficient).isEqualTo(10.0)
                assertThat(index).isEqualTo(4)
            }
        }
    }

    @Nested
    inner class PlusMinusTests {

        @Test
        fun `Should be able to create linear expression builder on plus operation`() {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = (20 + parameter).build()

            with(result) {
                assertThat(coefficients).containsExactly(parameter.coefficient)
                assertThat(constant).isEqualTo(20.0)
            }
        }

        @Test
        fun `Should be able to create linear expression builder on minus operation`() {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = (20 - parameter).build()

            with(result) {
                assertThat(coefficients).containsExactly(-parameter.coefficient)
                assertThat(constant).isEqualTo(20.0)
            }
        }
    }
}
