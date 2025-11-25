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

object Climb : SubsystemBase() {
    val motor = UniversalTalonFX(
        climbPort,
        config = climbConfig,
        simGains = pid
    )

    var setPoint = 0.0.meters
    var positionMeters = 0.0.meters

    val positionRequest = PositionVoltage(0.0)

    fun setVoltage(position: Distance): Command{
        return Commands.run({
            setPoint = position
            motor.setControl(
                positionRequest.withPosition(position.toAngle(diameter, gearRatio))
            )
            positionMeters = motor.inputs.position.toDistance(diameter, gearRatio)
        })
    }

    fun getUp(): Command{
        return setVoltage(positionUp)
    }

    fun getDown(): Command{
        return setVoltage(positionDown)
    }

    override fun periodic() {
        motor.updateInputs()
        Logger.processInputs(name, motor.inputs)
        Logger.recordOutput("setPoint", setPoint)
        Logger.recordOutput("position", positionMeters)
    }
}