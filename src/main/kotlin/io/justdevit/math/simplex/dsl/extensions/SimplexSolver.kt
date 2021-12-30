package io.justdevit.math.simplex.dsl.extensions

import io.justdevit.math.simplex.dsl.model.SimplexConfiguration
import org.apache.commons.math3.optim.PointValuePair
import org.apache.commons.math3.optim.linear.NonNegativeConstraint
import org.apache.commons.math3.optim.linear.SimplexSolver

/**
 * Calculates optimal solution.
 *
 * @param config Simplex configuration for calculation.
 * @return Optimal solution for configuration.
 *
 * @see SimplexSolver
 */
fun SimplexSolver.optimize(config: SimplexConfiguration): PointValuePair =
    optimize(
        config.linearConstraints,
        config.objectiveFunction,
        config.goal,
        NonNegativeConstraint(config.strictNonNegativeParameters)
    )
