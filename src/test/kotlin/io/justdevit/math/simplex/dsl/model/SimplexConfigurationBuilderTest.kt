package io.justdevit.math.simplex.dsl.model

import io.justdevit.math.simplex.dsl.extensions.plus
import io.justdevit.math.simplex.dsl.extensions.times
import io.justdevit.math.simplex.dsl.model.Goal.MAX
import io.justdevit.math.simplex.dsl.model.Goal.MIN
import io.justdevit.math.simplex.dsl.x1
import io.justdevit.math.simplex.dsl.x2
import io.justdevit.math.simplex.dsl.x3
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE
import org.junit.jupiter.api.assertThrows

class SimplexConfigurationBuilderTest : FreeSpec({


    with(SimplexConfigurationBuilder()) {
        mapOf(
            1.x1 to 1,
            1.x2 to 2,
            1.x3 to 3,
            1.x4 to 4,
            1.x5 to 5,
            1.x6 to 6,
            1.x7 to 7,
            1.x8 to 8,
            1.x9 to 9,
        ).forEach { (parameter, expectedIndex) ->
            "Parameter should have correct index" {
                parameter.coefficient shouldBe 1.0
                parameter.index shouldBe expectedIndex
            }
        }
    }

    "BuildTests" - {

        "Should reject build without linear constraints" {
            val builder = SimplexConfigurationBuilder()
            with(builder) {
                2 * x1 to MAX
            }

            assertThrows<IllegalStateException> { builder.build(true) }
        }

        "Should reject build without linear objective function" {
            val builder = SimplexConfigurationBuilder()
            with(builder) {
                x1 eq 10
            }

            assertThrows<IllegalStateException> { builder.build(true) }
        }

        "Should be able to build simplex configuration" {
            val builder = SimplexConfigurationBuilder()
            with(builder) {
                x1 ge 10
                x2 le 20
                x1 + x3 ge 50
                x1 to MIN
            }

            val result = builder.build(true)

            with(result) {
                linearConstraints.constraints shouldHaveSize 3
                objectiveFunction.coefficients.getEntry(0) shouldBe 1.0
                objectiveFunction.constantTerm shouldBe 0.0
                goal shouldBe MINIMIZE
                strictNonNegativeParameters.shouldBeTrue()
            }
        }
    }

})
