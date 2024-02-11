package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.Parameter
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class ParameterTest : FreeSpec({

    "Negate Tests" - {

        "Should be able to negate parameter" {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = parameter.negate()

            with(result) {
                coefficient shouldBe -parameter.coefficient
                index shouldBe parameter.index
            }
        }
    }

    "Plus Parameter Tests" - {

        "Should be able to create linear expression builder on plus operation" {
            val p1 = Parameter(coefficient = 10.0, index = 1)
            val p2 = Parameter(coefficient = 20.0, index = 2)

            val result = (p1 + p2).build()

            with(result) {
                coefficients shouldContainExactly listOf(p1.coefficient, p2.coefficient)
                constant shouldBe 0.0
            }
        }
    }

    "Plus Minus Number Tests" - {

        "Should be able to create linear expression builder on plus operation" {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = (parameter + 20).build()

            with(result) {
                coefficients shouldContainExactly listOf(parameter.coefficient)
                constant shouldBe 20.0
            }
        }

        "Should be able to create linear expression builder on minus operation" {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = (parameter - 20).build()

            with(result) {
                coefficients shouldContainExactly listOf(parameter.coefficient)
                constant shouldBe -20.0
            }
        }
    }
})
