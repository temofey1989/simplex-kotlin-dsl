package io.justdevit.math.simplex.dsl

import io.justdevit.math.simplex.dsl.extensions.plus
import io.justdevit.math.simplex.dsl.extensions.times
import io.justdevit.math.simplex.dsl.model.Goal.MAX
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset.offset
import org.junit.jupiter.api.Test

internal class SimplexDSLTest {

    @Test
    fun `Example 1`() {
        val result = simplex {
            0.3 * x1 + 0.5 * x2 + 0.4 * x3 le 20
            8.x1 + 7.x2 + 12.x3 le 480
            x1 + x2 eq 16
            x3 ge 25

            20.x1 + 55.x2 + 60.x3 to MAX
        }

        assertThat(result.point[0]).isEqualTo(0.0)
        assertThat(result.point[1]).isEqualTo(16.0)
        assertThat(result.point[2]).isCloseTo(30.0, offset(0.00001))
        assertThat(result.value).isEqualTo(2680.0)
    }
}
