package frc.robot.subsystems.shooter.flywheel

import com.ctre.phoenix6.controls.VelocityVoltage
import edu.wpi.first.units.measure.AngularVelocity
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.rps
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.littletonrobotics.junction.Logger

object Flywheel : SubsystemBase(){
    private val motor = UniversalTalonFX(
        port = PORT,
        config = CONFIG,
        simGains = PID_GAINS
    )

    var setpoint = 0.0.rps
    private val velocityRequest = VelocityVoltage(0.0)

    private fun setVelocity(velocity: AngularVelocity): Command {
        return Commands.runOnce({
            setpoint = velocity
            motor.setControl(velocityRequest.withVelocity(velocity))
        })
    }

    fun shoot(): Command = setVelocity(SHOOT_VELOCITY)

    fun backIn(): Command = setVelocity(BACK_IN_VELOCITY)

    fun stop(): Command = setVelocity(STOP_VELOCITY)

    override fun periodic() {
        motor.updateInputs()
        Logger.processInputs("Subsystems/shooter/flywheel", motor.inputs)
    }
}