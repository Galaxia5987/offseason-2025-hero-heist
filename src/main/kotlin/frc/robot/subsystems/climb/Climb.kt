package frc.robot.subsystems.climb

import com.ctre.phoenix6.controls.PositionVoltage
import edu.wpi.first.units.measure.Distance
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import frc.robot.lib.extensions.meters
import frc.robot.lib.extensions.toAngle
import frc.robot.lib.universal_motor.UniversalTalonFX

class Climb {
    val motor = UniversalTalonFX(
        climbPort,
        config = climbConfig,
        simGains = pid
    )

    var setpoint = 0.0.meters

    val positionRequest = PositionVoltage(0.0)

    fun setVoltage(position: Distance): Command{
        return Commands.runOnce({
            setpoint = position
            motor.setControl(
                positionRequest.withPosition(position.toAngle(diameter, gearRatio))
            )
        })
    }

    fun getUp(): Command{
        return setVoltage(positionUp)
    }

    fun getDown(): Command{
        return setVoltage(positionDown)
    }
}