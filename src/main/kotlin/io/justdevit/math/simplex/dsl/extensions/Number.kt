package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.LinearExpressionBuilder
import io.justdevit.math.simplex.dsl.model.Parameter
import io.justdevit.math.simplex.dsl.model.X

/**
 * Creates parameter for index.
 *
 * @param index Index of the parameter.
 * @return Indexed parameter.
 *
 * @see Parameter
 */
fun Number.x(index: Int) = Parameter(coefficient = toDouble(), index = index)

/**
 * Creates parameter for X-parameter.
 *
 * @param x X-parameter.
 * @return Indexed parameter.
 *
 * @see Parameter
 * @see X
 */
operator fun Number.times(x: X) = Parameter(coefficient = toDouble(), index = x.index)

/**
 * Creates linear expression builder for parameter.
 *
 * @param parameter Parameter.
 * @return Instance of the expression builder.
 *
 * @see LinearExpressionBuilder
 * @see Parameter
 */
operator fun Number.plus(parameter: Parameter): LinearExpressionBuilder {
    val builder = LinearExpressionBuilder()
    builder.add(parameter)
    builder.plus(this)
    return builder
}

/**
 * Creates linear expression builder for parameter.
 * Parameter will be negated.
 *
 * @param parameter Parameter.
 * @return Instance of the expression builder.
 *
 * @see LinearExpressionBuilder
 * @see Parameter
 */
operator fun Number.minus(parameter: Parameter) = plus(parameter.negate())
