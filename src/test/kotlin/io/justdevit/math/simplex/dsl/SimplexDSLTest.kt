package io.justdevit.math.simplex.dsl

import io.justdevit.math.simplex.dsl.extensions.plus
import io.justdevit.math.simplex.dsl.extensions.times
import io.justdevit.math.simplex.dsl.model.Goal.MAX
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.shouldBe

class SimplexDSLTest : FreeSpec({

    "Simple example" {
        val result = simplex {
            0.3 * x1 + 0.5 * x2 + 0.4 * x3 le 20
            8.x1 + 7.x2 + 12.x3 le 480
            x1 + x2 eq 16
            x3 ge 25

            20.x1 + 55.x2 + 60.x3 to MAX
        }

        result.point[0] shouldBe 0.0
        result.point[1] shouldBe 16.0
        result.point[2] shouldBe closeTo(30.0, 0.00001)
        result.value shouldBe 2680.0
    }
})

fun closeTo(number: Double, offset: Double): Matcher<Double> = object : Matcher<Double> {
    override fun test(value: Double) = MatcherResult.Companion.invoke(
        passed = value in (number - offset)..(number + offset),
        failureMessageFn = { "$value is not closed to $number with offset $offset" },
        negatedFailureMessageFn = { "$value is closed to $number with offset $offset" },
    )
}
