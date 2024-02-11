package io.justdevit.math.simplex.dsl.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class ParameterTest : FreeSpec({

    listOf(-1, 0).forEach {
        "Should reject negative or zero index" {
            shouldThrow<IllegalArgumentException> { Parameter(coefficient = 1.0, index = it) }
        }
    }

})
