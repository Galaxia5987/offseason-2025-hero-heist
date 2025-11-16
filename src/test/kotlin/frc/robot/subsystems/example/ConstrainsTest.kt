package frc.robot.subsystems.example

import edu.wpi.first.math.controller.ProfiledPIDController
import edu.wpi.first.math.trajectory.TrapezoidProfile
import kotlin.test.BeforeTest
import kotlin.test.assertTrue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConstrainsTest {
    lateinit var constraints: TrapezoidProfile.Constraints
    lateinit var controller: ProfiledPIDController

    @BeforeTest
    fun init() {
        constraints = TrapezoidProfile.Constraints(4.0, 3000.0)

        controller = ProfiledPIDController(3.0, 0.0, 0.0, constraints, 0.02)
        controller.reset(0.0)
    }

    @Test
    fun checkConstraintWork() {
        val constraints = TrapezoidProfile.Constraints(20.0, 20.0)
        controller.constraints = constraints
        assertTrue {
            controller.constraints.maxVelocity == constraints.maxVelocity
        }
    }

    @Test
    fun checkConstrains() {
        val goal = 4.0
        controller.setGoal(goal)

        var position = 0.0

        repeat(200) { _ ->
            val velocity = controller.calculate(position)
            println(velocity)
            position += velocity * controller.period
        }

        // After running long enough, we should be close to goal
        assertTrue(position > 0.45, "Should not undershoot the goal $position")
        assertTrue(
            position < 0.55,
            "Should not overshoot excessively either $position"
        )

        // Check that the internal setpoint never hit speed or accel limits unnecessarily
        val setpoint = controller.setpoint

        Assertions.assertTrue(
            setpoint.velocity <= constraints.maxVelocity + 1e-9,
            "Velocity should not be artificially limited"
        )

        Assertions.assertTrue(
            controller.goal.position == goal,
            "Goal must remain correct"
        )
    }
}
