package io.justdevit.math.simplex.dsl.model

import io.justdevit.math.simplex.dsl.x1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class LinearExpressionBuilderTest {

    @Test
    fun `Should be able to create empty linear expression`() {
        val builder = LinearExpressionBuilder()

        val result = builder.build()

        with(result) {
            assertThat(coefficients).isEmpty()
            assertThat(constant).isEqualTo(0.0)
        }
    }

    @Nested
    inner class AddingParameterTests {

        @Test
        fun `Should be able to add parameter`() {
            val builder = LinearExpressionBuilder()
            val parameter = Parameter(coefficient = 10.0, index = 1)

            builder.add(parameter)

            with(builder.build()) {
                assertThat(coefficients).containsExactly(parameter.coefficient)
                assertThat(constant).isEqualTo(0.0)
            }
        }

        @Test
        fun `Should be able to add parameter on index`() {
            val builder = LinearExpressionBuilder()
            val parameter = Parameter(coefficient = 10.0, index = Random.Default.nextInt(from = 1, until = 10))

            builder.add(parameter)

            with(builder.build()) {
                val expectedCoefficients = buildList {
                    repeat(parameter.index - 1) {
                        add(0.0)
                    }
                    add(parameter.coefficient)
                }
                assertThat(coefficients).containsExactly(*expectedCoefficients.toTypedArray())
                assertThat(constant).isEqualTo(0.0)
            }
        }
    }

    @Nested
    inner class PlusMinusParameterTests {

        @Test
        fun `Should be able to provide plus operation for parameter`() {
            val builder = LinearExpressionBuilder()
            val parameter = Parameter(coefficient = 10.0, index = 1)

            builder.plus(parameter)

            with(builder.build()) {
                assertThat(coefficients).containsExactly(parameter.coefficient)
                assertThat(constant).isEqualTo(0.0)
            }
        }

        @Test
        fun `Should be able to provide minus operation for parameter`() {
            val builder = LinearExpressionBuilder()
            val parameter = Parameter(coefficient = 10.0, index = 1)

            builder.minus(parameter)

            with(builder.build()) {
                assertThat(coefficients).containsExactly(-parameter.coefficient)
                assertThat(constant).isEqualTo(0.0)
            }
        }
    }

    @Nested
    inner class PlusMinusXTests {

        @Test
        fun `Should be able to provide plus operation for X`() {
            val builder = LinearExpressionBuilder()

            builder.plus(x1)

            with(builder.build()) {
                assertThat(coefficients).containsExactly(1.0)
                assertThat(constant).isEqualTo(0.0)
            }
        }

        @Test
        fun `Should be able to provide minus operation for X`() {
            val builder = LinearExpressionBuilder()

            builder.minus(x1)

            with(builder.build()) {
                assertThat(coefficients).containsExactly(-1.0)
                assertThat(constant).isEqualTo(0.0)
            }
        }
    }

    @Nested
    inner class PlusMinusNumberTests {

        @Test
        fun `Should be able to provide plus operation for number`() {
            val builder = LinearExpressionBuilder()

            builder.plus(1.0)

            with(builder.build()) {
                assertThat(coefficients).isEmpty()
                assertThat(constant).isEqualTo(1.0)
            }
        }

        @Test
        fun `Should be able to provide minus operation for number`() {
            val builder = LinearExpressionBuilder()

            builder.minus(1.0)

            with(builder.build()) {
                assertThat(coefficients).isEmpty()
                assertThat(constant).isEqualTo(-1.0)
            }
        }
    }
}
