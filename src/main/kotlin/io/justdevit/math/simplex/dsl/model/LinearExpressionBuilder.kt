package io.justdevit.math.simplex.dsl.model

import io.justdevit.math.simplex.dsl.SimplexDslMarker
import io.justdevit.math.simplex.dsl.extensions.negate

/**
 * Linear expression builder.
 *
 * @see LinearExpression
 */
@SimplexDslMarker
class LinearExpressionBuilder {

    /**
     * Map of index to parameters.
     */
    private val parameters: MutableMap<Int, Parameter> = mutableMapOf()

    /**
     * Constant part of the linear expression.
     */
    private var constant: Double = 0.0

    /**
     * Maximal index of the linear expression.
     * Used for coefficient list.
     */
    private var maxIndex: Int = -1

    /**
     * Adds parameter to the list of parameters.
     * In case of parameter exists with such index, coefficient will be summarized.
     *
     * @param parameter Parameter to add.
     * @return This instance of the builder.
     */
    fun add(parameter: Parameter): LinearExpressionBuilder {
        var value = parameters[parameter.index]
        value = value?.copy(coefficient = value.coefficient + parameter.coefficient) ?: parameter
        parameters[parameter.index] = value

        if (parameter.index > maxIndex) {
            maxIndex = parameter.index
        }
        return this
    }

    /**
     * Adds parameter to the builder.
     *
     * @param parameter Parameter to add.
     * @return This instance of the builder.
     */
    operator fun plus(parameter: Parameter) = add(parameter)

    /**
     * Adds negated parameter to the builder.
     *
     * @param parameter Parameter to add.
     * @return This instance of the builder.
     *
     * @see Parameter
     */
    operator fun minus(parameter: Parameter) = add(parameter.negate())

    /**
     * Adds number value to constant value.
     *
     * @param number Number to add.
     * @return This instance of the builder.
     *
     * @see Parameter
     */
    operator fun plus(number: Number): LinearExpressionBuilder {
        constant += number.toDouble()
        return this
    }

    /**
     * Removes number value to constant value.
     *
     * @param number Number to remove.
     * @return This instance of the builder.
     */
    operator fun minus(number: Number): LinearExpressionBuilder {
        return plus(-number.toDouble())
    }

    /**
     * Adds parameter to the builder based on X value.
     *
     * @param x X-value.
     * @return This instance of the builder.
     *
     * @see X
     */
    operator fun plus(x: X): LinearExpressionBuilder = add(Parameter(coefficient = 1.0, index = x.index))

    /**
     * Adds negated parameter to the builder based on X value.
     *
     * @param x X-value.
     * @return This instance of the builder.
     *
     * @see X
     */
    operator fun minus(x: X): LinearExpressionBuilder = add(Parameter(coefficient = -1.0, index = x.index))

    /**
     * Builds instance of the linear expression.
     *
     * @return Instance of the linear expression.
     *
     * @see LinearExpression
     */
    fun build() = LinearExpression(
        coefficients = coefficientList,
        constant = constant
    )

    /**
     * List of coefficients based on maximal index.
     */
    private val coefficientList: List<Double>
        get() {
            if (maxIndex < 0) {
                return emptyList()
            }
            val result = DoubleArray(maxIndex) { 0.0 }
            parameters.forEach { result[it.key - 1] = it.value.coefficient }
            return result.toList()
        }
}
