package frc.robot.subsystems.climb

import com.ctre.phoenix6.controls.PositionVoltage
import edu.wpi.first.units.measure.Distance
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.meters
import frc.robot.lib.extensions.toAngle
import frc.robot.lib.extensions.toDistance
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.littletonrobotics.junction.Logger
import org.team5987.annotation.LoggedOutput

object Climb : SubsystemBase() {
    val motor = UniversalTalonFX(
        PORT,
        config = CONFIG,
        simGains = PID_GAINS
    )

    @LoggedOutput
    var setpoint = 0.0.meters
    var positionMeters = 0.0.meters

    private val positionRequest = PositionVoltage(0.0)

    fun setPosition(position: Distance): Command{
        return Commands.run({
            setpoint = position
            motor.setControl(
                positionRequest.withPosition(position.toAngle(DIAMETER, GEAR_RATIO))
            )
        })
    }

    fun getUp(): Command = setPosition(POSITION_UP)

    fun getDown(): Command = setPosition(POSITION_DOWN)


    override fun periodic() {
        motor.updateInputs()
        Logger.processInputs(name, motor.inputs)
        Logger.recordOutput("position", motor.inputs.position.toDistance(DIAMETER, GEAR_RATIO))
    }
}