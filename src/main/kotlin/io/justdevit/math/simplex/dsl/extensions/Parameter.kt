package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.LinearExpressionBuilder
import io.justdevit.math.simplex.dsl.model.Parameter

/**
 * Negates parameter.
 *
 * @return Negated parameter.
 */
fun Parameter.negate() = copy(coefficient = -coefficient)

/**
 * Creates linear expression builder for parameter.
 *
 * @param other Parameter.
 * @return Instance of the expression builder.
 *
 * @see LinearExpressionBuilder
 * @see Parameter
 */
operator fun Parameter.plus(other: Parameter): LinearExpressionBuilder {
    val builder = LinearExpressionBuilder()
    builder.add(this)
    builder.add(other)
    return builder
}

/**
 * Creates linear expression builder for parameter.
 *
 * @param number Number to be added.
 * @return Instance of the expression builder.
 *
 * @see LinearExpressionBuilder
 * @see Parameter
 */
operator fun Parameter.plus(number: Number) = number.plus(this)

/**
 * Creates linear expression builder for parameter.
 *
 * @param number Number to be added.
 * @return Instance of the expression builder.
 *
 * @see LinearExpressionBuilder
 * @see Parameter
 */
operator fun Parameter.minus(number: Number) = (-number.toDouble()).plus(this)


