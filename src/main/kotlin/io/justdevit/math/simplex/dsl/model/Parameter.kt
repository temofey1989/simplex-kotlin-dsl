package io.justdevit.math.simplex.dsl.model

/**
 * Represents parameter for linear expression.
 */
data class Parameter(

    /**
     * Coefficient of the parameter.
     */
    val coefficient: Double,

    /**
     * Index of the parameter.
     * Index should be a number greater than 1.
     */
    val index: Int
) {

    init {
        require(index > 0) {
            "Index should be a number greater than 1."
        }
    }
}
