package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.Parameter
import io.justdevit.math.simplex.dsl.x1
import io.justdevit.math.simplex.dsl.x5
import io.justdevit.math.simplex.dsl.x6
import io.justdevit.math.simplex.dsl.x7
import io.justdevit.math.simplex.dsl.x8
import io.justdevit.math.simplex.dsl.x9
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class XTest : FreeSpec({

    "Should be able to create instance of the X" {
        val result = x(index = 1)

        result.index shouldBe 1
    }

    "Should be able to create parameter on unary minus operation" {
        val result = -x6

        with(result) {
            coefficient shouldBe -1.0
            index shouldBe 6
        }
    }

    "Times Tests" - {

        "Should be able to create parameter on times operation" {
            val result = x5 * 10

            with(result) {
                coefficient shouldBe 10.0
                index shouldBe 5
            }
        }
    }

    "Operations with X-Tests" - {

        "Should be able to create linear expression builder on plus operation" {
            val result = (x6 + x7).build()

            with(result) {
                coefficients shouldContainExactly listOf(0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0)
                constant shouldBe 0.0
            }
        }

        "Should be able to create linear expression builder on minus operation" {
            val result = (x8 - x9).build()

            with(result) {
                coefficients shouldContainExactly listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, -1.0)
                constant shouldBe 0.0
            }
        }
    }

    "Operations with Numbers Tests" - {

        "Should be able to create linear expression builder on plus operation" {
            val result = (x1 + 10).build()

            with(result) {
                coefficients shouldContainExactly listOf(1.0)
                constant shouldBe 10.0
            }
        }

        "Should be able to create linear expression builder on minus operation" {
            val result = (x1 - 10).build()

            with(result) {
                coefficients shouldContainExactly listOf(1.0)
                constant shouldBe -10.0
            }
        }
    }

    "OperationsWithParametersTests" - {

        "Should be able to create linear expression builder on plus operation" {
            val parameter = Parameter(coefficient = 10.0, index = 2)

            val result = (x1 + parameter).build()

            with(result) {
                coefficients shouldContainExactly listOf(1.0, 10.0)
                constant shouldBe 0.0
            }
        }

        "Should be able to create linear expression builder on minus operation" {
            val parameter = Parameter(coefficient = 10.0, index = 2)

            val result = (x1 - parameter).build()

            with(result) {
                coefficients shouldContainExactly listOf(1.0, -10.0)
                constant shouldBe 0.0
            }
        }
    }
})
