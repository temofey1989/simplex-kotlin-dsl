package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.LinearExpressionBuilder
import io.justdevit.math.simplex.dsl.model.Parameter
import io.justdevit.math.simplex.dsl.model.X

/**
 * Creates instance of the X-parameter.
 *
 * @param index Parameter index.
 */
fun x(index: Int = 1) = X(index)

/**
 * Creates parameter with negated coefficient.
 *
 * @return Parameter with negated coefficient.
 */
operator fun X.unaryMinus() = Parameter(coefficient = -1.0, index = index)

/**
 * Creates parameter with number for X-parameter.
 *
 * @return Parameter based on X-parameter and number.
 */
operator fun X.times(number: Number) = Parameter(coefficient = number.toDouble(), index = index)

/**
 * Creates linear expression builder with two X-parameters.
 *
 * @return Instance of the linear expression builder.
 */
operator fun X.plus(other: X): LinearExpressionBuilder {
    val builder = LinearExpressionBuilder()
    builder.add(Parameter(coefficient = 1.0, index = index))
    builder.add(Parameter(coefficient = 1.0, index = other.index))
    return builder
}

/**
 * Creates linear expression builder with two X-parameters (second with negated coefficient).
 *
 * @return Instance of the linear expression builder.
 */
operator fun X.minus(other: X): LinearExpressionBuilder {
    val builder = LinearExpressionBuilder()
    builder.add(Parameter(coefficient = 1.0, index = index))
    builder.add(Parameter(coefficient = -1.0, index = other.index))
    return builder
}

/**
 * Creates linear expression builder with parameter and constant.
 *
 * @return Instance of the linear expression builder.
 */
operator fun X.plus(number: Number): LinearExpressionBuilder {
    val builder = LinearExpressionBuilder()
    builder.add(Parameter(coefficient = 1.0, index = index))
    builder.plus(number)
    return builder
}

/**
 * Creates linear expression builder with parameter and negated constant.
 *
 * @return Instance of the linear expression builder.
 */
operator fun X.minus(number: Number): LinearExpressionBuilder = plus(-number.toDouble())

/**
 * Creates linear expression builder with two parameters.
 * First is created based on X-parameter.
 *
 * @return Instance of the linear expression builder.
 */
operator fun X.plus(parameter: Parameter): LinearExpressionBuilder {
    val builder = LinearExpressionBuilder()
    builder.add(Parameter(coefficient = 1.0, index = index))
    builder.add(parameter)
    return builder
}

/**
 * Creates linear expression builder with two parameters.
 * First is created based on X-parameter.
 * Second with negated coefficient.
 *
 * @return Instance of the linear expression builder.
 */
operator fun X.minus(parameter: Parameter): LinearExpressionBuilder {
    val builder = LinearExpressionBuilder()
    builder.add(Parameter(coefficient = 1.0, index = index))
    builder.add(parameter.copy(coefficient = -parameter.coefficient))
    return builder
}
