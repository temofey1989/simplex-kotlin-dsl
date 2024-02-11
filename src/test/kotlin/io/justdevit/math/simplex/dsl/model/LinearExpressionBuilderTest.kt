package io.justdevit.math.simplex.dsl.model

import io.justdevit.math.simplex.dsl.x1
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class LinearExpressionBuilderTest : FreeSpec({

    "Should be able to create empty linear expression" {
        val builder = LinearExpressionBuilder()

        val result = builder.build()

        with(result) {
            coefficients.shouldBeEmpty()
            constant shouldBe 0.0
        }
    }

    "Adding Parameter Tests" - {

        "Should be able to add parameter" {
            val builder = LinearExpressionBuilder()
            val parameter = Parameter(coefficient = 10.0, index = 1)

            builder.add(parameter)

            with(builder.build()) {
                coefficients shouldContainExactly listOf(parameter.coefficient)
                constant shouldBe 0.0
            }
        }

        "Should be able to add parameter on index" {
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
                coefficients shouldContainExactly expectedCoefficients
                constant shouldBe 0.0
            }
        }
    }

    "Plus Minus Parameter Tests" - {

        "Should be able to provide plus operation for parameter" {
            val builder = LinearExpressionBuilder()
            val parameter = Parameter(coefficient = 10.0, index = 1)

            builder.plus(parameter)

            with(builder.build()) {
                coefficients shouldContainExactly listOf(parameter.coefficient)
                constant shouldBe 0.0
            }
        }

        "Should be able to provide minus operation for parameter" {
            val builder = LinearExpressionBuilder()
            val parameter = Parameter(coefficient = 10.0, index = 1)

            builder.minus(parameter)

            with(builder.build()) {
                coefficients shouldContainExactly listOf(-parameter.coefficient)
                constant shouldBe 0.0
            }
        }
    }

    "Plus Minus X-Tests" - {

        "Should be able to provide plus operation for X" {
            val builder = LinearExpressionBuilder()

            builder.plus(x1)

            with(builder.build()) {
                coefficients shouldContainExactly listOf(1.0)
                constant shouldBe 0.0
            }
        }

        "Should be able to provide minus operation for X" {
            val builder = LinearExpressionBuilder()

            builder.minus(x1)

            with(builder.build()) {
                coefficients shouldContainExactly listOf(-1.0)
                constant shouldBe 0.0
            }
        }
    }

    "Plus Minus Number Tests" - {

        "Should be able to provide plus operation for number" {
            val builder = LinearExpressionBuilder()

            builder.plus(1.0)

            with(builder.build()) {
                coefficients.shouldBeEmpty()
                constant shouldBe 1.0
            }
        }

        "Should be able to provide minus operation for number" {
            val builder = LinearExpressionBuilder()

            builder.minus(1.0)

            with(builder.build()) {
                coefficients.shouldBeEmpty()
                constant shouldBe -1.0
            }
        }
    }
})
