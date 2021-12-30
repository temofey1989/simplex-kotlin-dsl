package io.justdevit.math.simplex.dsl.model

import org.apache.commons.math3.optim.linear.LinearConstraintSet
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType

/**
 * Represents configuration for Apache Simplex solver.
 */
data class SimplexConfiguration(

    /**
     * Linear Constraints Set.
     */
    val linearConstraints: LinearConstraintSet,

    /**
     * Linear Objective function.
     */
    val objectiveFunction: LinearObjectiveFunction,

    /**
     * Goal of the objective function.
     */
    val goal: GoalType,

    /**
     * Represents a condition for non-negative parameters.
     */
    val strictNonNegativeParameters: Boolean = true

)
