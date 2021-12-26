package io.justdevit.math.simplex.dsl.model

import org.apache.commons.math3.optim.nonlinear.scalar.GoalType
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MAXIMIZE
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE

/**
 * Represents minimal version of Linear Objective function goal.
 */
enum class Goal(

    /**
     * Goal type from Apache Common Math library.
     */
    val type: GoalType

) {
    MIN(MINIMIZE),
    MAX(MAXIMIZE)
}
