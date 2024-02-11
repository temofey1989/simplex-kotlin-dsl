package io.justdevit.math.simplex.dsl.model

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class LinearExpressionTest : FreeSpec({

    "Should be able to map to linear objective function" {
        val expression = LinearExpression(listOf(10.0), 20.0)

        val result = expression.toLinearObjectiveFunction()

        with(result) {
            coefficients.dimension shouldBe 1
            coefficients.getEntry(0) shouldBe 10.0
            constantTerm shouldBe 20.0
        }
    }
})
