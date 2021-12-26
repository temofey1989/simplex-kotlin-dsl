package io.justdevit.math.simplex.dsl.model

import io.justdevit.math.simplex.dsl.SimplexDslMarker
import io.justdevit.math.simplex.dsl.extensions.x
import org.apache.commons.math3.optim.linear.LinearConstraint
import org.apache.commons.math3.optim.linear.LinearConstraintSet
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction
import org.apache.commons.math3.optim.linear.Relationship
import org.apache.commons.math3.optim.linear.Relationship.EQ
import org.apache.commons.math3.optim.linear.Relationship.GEQ
import org.apache.commons.math3.optim.linear.Relationship.LEQ

/**
 * Represents a builder for Simplex configuration.
 */
@SimplexDslMarker
class SimplexConfigurationBuilder {

    /**
     * List of linear constraint configurations.
     */
    private val linearConstraintConfigs = mutableListOf<LinearConstraintConfig>()

    /**
     * Linear Objective Function.
     */
    private var objectiveFunction: LinearObjectiveFunction? = null

    /**
     * Goal of the linear objective function.
     */
    private var goal: Goal? = null

    // ---------------------------------------------

    /**
     * Returns parameter with index 1.
     */
    val Int.x1: Parameter
        get() = x(1)

    /**
     * Returns parameter with index 2.
     */
    val Int.x2: Parameter
        get() = x(2)

    /**
     * Returns parameter with index 3.
     */
    val Int.x3: Parameter
        get() = x(3)

    /**
     * Returns parameter with index 4.
     */
    val Int.x4: Parameter
        get() = x(4)

    /**
     * Returns parameter with index 5.
     */
    val Int.x5: Parameter
        get() = x(5)

    /**
     * Returns parameter with index 6.
     */
    val Int.x6: Parameter
        get() = x(6)

    /**
     * Returns parameter with index 7.
     */
    val Int.x7: Parameter
        get() = x(7)

    /**
     * Returns parameter with index 8.
     */
    val Int.x8: Parameter
        get() = x(8)

    /**
     * Returns parameter with index 9.
     */
    val Int.x9: Parameter
        get() = x(9)

    // ---------------------------------------------

    /**
     * Adds less or equal linear constraint on linear expression builder.
     *
     * @param value Value to linear equation.
     */
    infix fun LinearExpressionBuilder.le(value: Number) {
        this@SimplexConfigurationBuilder.linearConstraintConfigs += LinearConstraintConfig(build(), LEQ, value.toDouble())
    }

    /**
     * Adds greater or equal linear constraint on linear expression builder.
     *
     * @param value Value to linear equation.
     */
    infix fun LinearExpressionBuilder.ge(value: Number) {
        this@SimplexConfigurationBuilder.linearConstraintConfigs += LinearConstraintConfig(build(), GEQ, value.toDouble())
    }

    /**
     * Adds equal linear constraint on linear expression builder.
     *
     * @param value Value to linear equation.
     */
    infix fun LinearExpressionBuilder.eq(value: Number) {
        this@SimplexConfigurationBuilder.linearConstraintConfigs += LinearConstraintConfig(build(), EQ, value.toDouble())
    }

    /**
     * Adds linear objective function on linear expression builder.
     *
     * @param goal Goal of the linear objective function.
     */
    infix fun LinearExpressionBuilder.to(goal: Goal) {
        this@SimplexConfigurationBuilder.objectiveFunction = build().toLinearObjectiveFunction()
        this@SimplexConfigurationBuilder.goal = goal
    }

    // ---------------------------------------------

    /**
     * Adds less or equal linear constraint on parameter.
     *
     * @param value Value to linear equation.
     */
    infix fun Parameter.le(value: Number) {
        linearConstraintConfigs += LinearConstraintConfig(
            expression = LinearExpressionBuilder().add(this).build(),
            relationship = LEQ,
            value = value.toDouble()
        )
    }

    /**
     * Adds greater or equal linear constraint on parameter.
     *
     * @param value Value to linear equation.
     */
    infix fun Parameter.ge(value: Number) {
        linearConstraintConfigs += LinearConstraintConfig(
            expression = LinearExpressionBuilder().add(this).build(),
            relationship = GEQ,
            value = value.toDouble()
        )
    }

    /**
     * Adds equal linear constraint on parameter.
     *
     * @param value Value to linear equation.
     */
    infix fun Parameter.eq(value: Number) {
        linearConstraintConfigs += LinearConstraintConfig(
            expression = LinearExpressionBuilder().add(this).build(),
            relationship = EQ,
            value = value.toDouble()
        )
    }

    /**
     * Adds linear objective function on parameter.
     *
     * @param goal Goal of the linear objective function.
     */
    infix fun Parameter.to(goal: Goal) {
        objectiveFunction = LinearObjectiveFunction(doubleArrayOf(coefficient), 0.0)
        this@SimplexConfigurationBuilder.goal = goal
    }

    // ---------------------------------------------

    /**
     * Adds less or equal linear constraint on X-parameter.
     *
     * @param value Value to linear equation.
     */
    infix fun X.le(value: Number) {
        Parameter(coefficient = 1.0, index = index).le(value)
    }

    /**
     * Adds greater or equal linear constraint on X-parameter.
     *
     * @param value Value to linear equation.
     */
    infix fun X.ge(value: Number) {
        Parameter(coefficient = 1.0, index = index).ge(value)
    }

    /**
     * Adds equal linear constraint on X-parameter.
     *
     * @param value Value to linear equation.
     */
    infix fun X.eq(value: Number) {
        Parameter(coefficient = 1.0, index = index).eq(value)
    }

    /**
     * Adds linear objective function on X-parameter.
     *
     * @param goal Goal of the linear objective function.
     */
    infix fun X.to(goal: Goal) {
        objectiveFunction = LinearObjectiveFunction(doubleArrayOf(1.0), 0.0)
        this@SimplexConfigurationBuilder.goal = goal
    }

    // ---------------------------------------------

    /**
     * Builds Simplex configuration.
     *
     * @param strictNonNegativeParameters Flag for non-negative parameters.
     * @return Instance of Simplex configuration.
     *
     * @see SimplexConfiguration
     */
    fun build(strictNonNegativeParameters: Boolean = true): SimplexConfiguration {
        if (linearConstraintConfigs.isEmpty()) {
            throw IllegalStateException("Linear constraints are not defined.")
        }
        if (objectiveFunction == null) {
            throw IllegalStateException("Objective function is not defined.")
        }
        if (goal == null) {
            throw IllegalStateException("Simplex goal is not defined.")
        }
        return SimplexConfiguration(
            linearConstraints = LinearConstraintSet(linearConstraints),
            objectiveFunction = objectiveFunction!!,
            goal = goal!!.type,
            strictNonNegativeParameters = strictNonNegativeParameters
        )
    }

    /**
     * Returns linear constraints based on configurations.
     */
    private val linearConstraints: List<LinearConstraint>
        get() {
            val biggestCoefficientIndex = linearConstraintConfigs.maxOf { it.expression.coefficients.size }
            return linearConstraintConfigs.map {
                LinearConstraint(
                    it.expression.coefficients.convertToSizedArray(biggestCoefficientIndex),
                    it.relationship,
                    it.value + it.expression.constant
                )
            }
        }

    /**
     * Converts list to matrix of target size.
     *
     * @param targetSize Target size of the matrix column.
     * @return Converted list to sized array.
     */
    private fun List<Double>.convertToSizedArray(targetSize: Int) =
        toDoubleArray().extentSizeTo(targetSize)

    /**
     * Extends array with zeros to target size.
     *
     * @param targetSize Target size of the array.
     * @return Extended array.
     */
    private fun DoubleArray.extentSizeTo(targetSize: Int) =
        if (targetSize == size) this else copyInto(DoubleArray(targetSize) { 0.0 })

    private data class LinearConstraintConfig(
        val expression: LinearExpression,
        val relationship: Relationship,
        val value: Double
    )
}
