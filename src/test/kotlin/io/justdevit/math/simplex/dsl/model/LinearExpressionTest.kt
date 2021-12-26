package io.justdevit.math.simplex.dsl.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LinearExpressionTest {

    @Test
    fun `Should be able to map to linear objective function`() {
        val expression = LinearExpression(listOf(10.0), 20.0)

        val result = expression.toLinearObjectiveFunction()

        with(result) {
            assertThat(coefficients.dimension).isEqualTo(1)
            assertThat(coefficients.getEntry(0)).isEqualTo(10.0)
            assertThat(constantTerm).isEqualTo(20.0)
        }
    }
}
