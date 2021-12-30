package io.justdevit.math.simplex.dsl.model

import io.justdevit.math.simplex.dsl.extensions.plus
import io.justdevit.math.simplex.dsl.extensions.times
import io.justdevit.math.simplex.dsl.model.Goal.MAX
import io.justdevit.math.simplex.dsl.model.Goal.MIN
import io.justdevit.math.simplex.dsl.x1
import io.justdevit.math.simplex.dsl.x2
import io.justdevit.math.simplex.dsl.x3
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource

internal class SimplexConfigurationBuilderTest {

    companion object {

        @JvmStatic
        fun parameterIndexData() = buildList<Arguments> {
            with(SimplexConfigurationBuilder()) {
                add(of(1.x1, 1))
                add(of(1.x2, 2))
                add(of(1.x3, 3))
                add(of(1.x4, 4))
                add(of(1.x5, 5))
                add(of(1.x6, 6))
                add(of(1.x7, 7))
                add(of(1.x8, 8))
                add(of(1.x9, 9))
            }
        }.stream()
    }

    @ParameterizedTest
    @MethodSource("parameterIndexData")
    fun `Parameter should have correct index`(parameter: Parameter, expectedIndex: Int) {
        assertThat(parameter.coefficient).isEqualTo(1.0)
        assertThat(parameter.index).isEqualTo(expectedIndex)
    }

    @Nested
    inner class BuildTests {

        @Test
        fun `Should reject build without linear constraints`() {
            val builder = SimplexConfigurationBuilder()
            with(builder) {
                2 * x1 to MAX
            }

            assertThrows<IllegalStateException> { builder.build(true) }
        }

        @Test
        fun `Should reject build without linear objective function`() {
            val builder = SimplexConfigurationBuilder()
            with(builder) {
                x1 eq 10
            }

            assertThrows<IllegalStateException> { builder.build(true) }
        }

        @Test
        fun `Should be able to build simplex configuration`() {
            val builder = SimplexConfigurationBuilder()
            with(builder) {
                x1 ge 10
                x2 le 20
                x1 + x3 ge 50
                x1 to MIN
            }

            val result = builder.build(true)

            with(result) {
                assertThat(linearConstraints.constraints).hasSize(3)
                assertThat(objectiveFunction.coefficients.getEntry(0)).isEqualTo(1.0)
                assertThat(objectiveFunction.constantTerm).isEqualTo(0.0)
                assertThat(goal).isEqualTo(MINIMIZE)
                assertThat(strictNonNegativeParameters).isTrue
            }
        }
    }


}
