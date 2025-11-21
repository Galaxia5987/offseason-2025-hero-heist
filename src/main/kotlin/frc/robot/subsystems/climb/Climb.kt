package frc.robot.subsystems.climb

import com.ctre.phoenix6.controls.PositionVoltage
import edu.wpi.first.units.measure.Distance
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import frc.robot.lib.extensions.toAngle
import frc.robot.lib.universal_motor.UniversalTalonFX

class Climb {
    val motor = UniversalTalonFX(
        climbPort,
        config = climbConfig,
        simGains = pid
    )

    val positionRequest = PositionVoltage(0.0)

    fun setVoltage(distance: Distance) : Command{
        return Commands.runOnce({
            motor.setControl(
                positionRequest.withPosition(distance.toAngle(diameter, gearRatio))
            )
        })
    }

    fun getUp(){
        setVoltage(positionUp)
    }

    fun getDown(){
        setVoltage(positionDown)
    }
}