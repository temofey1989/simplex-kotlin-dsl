package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.Parameter
import io.justdevit.math.simplex.dsl.x1
import io.justdevit.math.simplex.dsl.x5
import io.justdevit.math.simplex.dsl.x6
import io.justdevit.math.simplex.dsl.x7
import io.justdevit.math.simplex.dsl.x8
import io.justdevit.math.simplex.dsl.x9
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class XTest {

    @Test
    fun `Should be able to create instance of the X`() {
        val result = x(index = 1)

        assertThat(result.index).isEqualTo(1)
    }

    @Test
    fun `Should be able to create parameter on unary minus operation`() {
        val result = -x6

        with(result) {
            assertThat(coefficient).isEqualTo(-1.0)
            assertThat(index).isEqualTo(6)
        }
    }

    @Nested
    inner class TimesTests {

        @Test
        fun `Should be able to create parameter on times operation`() {
            val result = x5 * 10

            with(result) {
                assertThat(coefficient).isEqualTo(10.0)
                assertThat(index).isEqualTo(5)
            }
        }
    }

    @Nested
    inner class OperationsWithXTests {

        @Test
        fun `Should be able to create linear expression builder on plus operation`() {
            val result = (x6 + x7).build()

            with(result) {
                assertThat(coefficients).containsExactly(0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0)
                assertThat(constant).isEqualTo(0.0)
            }
        }

        @Test
        fun `Should be able to create linear expression builder on minus operation`() {
            val result = (x8 - x9).build()

            with(result) {
                assertThat(coefficients).containsExactly(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, -1.0)
                assertThat(constant).isEqualTo(0.0)
            }
        }
    }

    @Nested
    inner class OperationsWithNumbersTests {

        @Test
        fun `Should be able to create linear expression builder on plus operation`() {
            val result = (x1 + 10).build()

            with(result) {
                assertThat(coefficients).containsExactly(1.0)
                assertThat(constant).isEqualTo(10.0)
            }
        }

        @Test
        fun `Should be able to create linear expression builder on minus operation`() {
            val result = (x1 - 10).build()

            with(result) {
                assertThat(coefficients).containsExactly(1.0)
                assertThat(constant).isEqualTo(-10.0)
            }
        }
    }

    @Nested
    inner class OperationsWithParametersTests {

        @Test
        fun `Should be able to create linear expression builder on plus operation`() {
            val parameter = Parameter(coefficient = 10.0, index = 2)

            val result = (x1 + parameter).build()

            with(result) {
                assertThat(coefficients).containsExactly(1.0, 10.0)
                assertThat(constant).isEqualTo(0.0)
            }
        }

        @Test
        fun `Should be able to create linear expression builder on minus operation`() {
            val parameter = Parameter(coefficient = 10.0, index = 2)

            val result = (x1 - parameter).build()

            with(result) {
                assertThat(coefficients).containsExactly(1.0, -10.0)
                assertThat(constant).isEqualTo(0.0)
            }
        }
    }
}
