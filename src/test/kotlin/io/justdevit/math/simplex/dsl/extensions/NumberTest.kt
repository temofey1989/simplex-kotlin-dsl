package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.Parameter
import io.justdevit.math.simplex.dsl.x4
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class NumberTest : FreeSpec({

    "X-Tests" - {

        "Should be able to create parameter" {
            val result = 10.x(index = 1)

            with(result) {
                coefficient shouldBe 10.0
                index shouldBe 1
            }
        }
    }

    "Times Tests" - {

        "Should be able to create parameter for times operation" {
            val result = 10 * x4

            with(result) {
                coefficient shouldBe 10.0
                index shouldBe 4
            }
        }
    }

    "Plus Minus Tests" - {

        "Should be able to create linear expression builder on plus operation" {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = (20 + parameter).build()

            with(result) {
                coefficients shouldContainExactly listOf(parameter.coefficient)
                constant shouldBe 20.0
            }
        }

        "Should be able to create linear expression builder on minus operation" {
            val parameter = Parameter(coefficient = 10.0, index = 1)

            val result = (20 - parameter).build()

            with(result) {
                coefficients shouldContainExactly listOf(-parameter.coefficient)
                constant shouldBe 20.0
            }
        }
    }
})
