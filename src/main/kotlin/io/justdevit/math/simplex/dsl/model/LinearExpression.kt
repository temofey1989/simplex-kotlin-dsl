package io.justdevit.math.simplex.dsl.model

import org.apache.commons.math3.optim.linear.LinearObjectiveFunction

/**
 * Represents linear expression.
 */
data class LinearExpression(

    /**
     * List of coefficients.
     */
    val coefficients: List<Double>,

    /**
     * Constant part of linear expression.
     */
    val constant: Double = 0.0

) {

    /**
     * Converts linear expression to linear objective function.
     *
     * @return Linear objective function.
     * @see LinearObjectiveFunction
     */
    fun toLinearObjectiveFunction() = LinearObjectiveFunction(coefficients.toDoubleArray(), constant)

}
