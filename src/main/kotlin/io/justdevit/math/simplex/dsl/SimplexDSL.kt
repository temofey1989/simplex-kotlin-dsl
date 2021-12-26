@file:Suppress("ClassName")

package io.justdevit.math.simplex.dsl

import io.justdevit.math.simplex.dsl.extensions.optimize
import io.justdevit.math.simplex.dsl.model.SimplexConfigurationBuilder
import io.justdevit.math.simplex.dsl.model.X
import org.apache.commons.math3.optim.PointValuePair
import org.apache.commons.math3.optim.linear.SimplexSolver

/**
 * Function to build a Simplex configuration and calculate the optimal solution.
 *
 * @param strictNonNegativeParameters Flag which indicated if negative parameters are possible.
 * @param block Builder lambda for Simplex configuration.
 * @return Optimal solution.
 *
 * @see SimplexSolver
 */
fun simplex(strictNonNegativeParameters: Boolean = true, block: SimplexConfigurationBuilder.() -> Unit): PointValuePair {
    val builder = SimplexConfigurationBuilder()
    builder.block()
    return SimplexSolver().optimize(builder.build(strictNonNegativeParameters))
}

/**
 * Represents singleton for X with index 1.
 */
object x1 : X(1)

/**
 * Represents singleton for X with index 2.
 */
object x2 : X(2)

/**
 * Represents singleton for X with index 3.
 */
object x3 : X(3)

/**
 * Represents singleton for X with index 4.
 */
object x4 : X(4)

/**
 * Represents singleton for X with index 5.
 */
object x5 : X(5)

/**
 * Represents singleton for X with index 6.
 */
object x6 : X(6)

/**
 * Represents singleton for X with index 7.
 */
object x7 : X(7)

/**
 * Represents singleton for X with index 8.
 */
object x8 : X(8)

/**
 * Represents singleton for X with index 9.
 */
object x9 : X(9)
