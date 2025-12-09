package frc.robot.subsystems.elvator.elvator

import com.ctre.phoenix6.controls.PositionVoltage
import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Distance
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.meters
import frc.robot.lib.extensions.toAngle
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.littletonrobotics.junction.Logger

object elevator: SubsystemBase() {
private val MainMotor=
    UniversalTalonFX(
        MOTOR_PORT,
        gearRatio = GEAR_RATIO,
        config = MOTOR_CONFIG
    )
    var height= {-> setPoint}
    var setPoint= 0.0.meters
    val voltageRequest= VoltageOut(0.0)
    fun setVoltage(voltage: Voltage): Command {
        return Commands.runOnce({MainMotor.setControl(voltageRequest.withOutput(voltage))})
    }
    val positionVoltage = PositionVoltage(0.0)
    fun setPosition(position: Distance): Command {
        setPoint= position
        return Commands.runOnce({MainMotor.setControl(positionVoltage.withPosition(
            position.toAngle(SPORCKET_DIAMETER, GEAR_RATIO)
        ))})
    }


    override fun periodic() {
        MainMotor.updateInputs()
        Logger.processInputs(name, MainMotor.inputs)
        Logger.recordOutput("levels", setPoint)
    }

   fun goToPurple_low(): Command {
       return Commands.runOnce({setPosition(ELEVATOR_HEIGHTS.PURPLE_LOW.position)})
   }

    fun goToGreen_low(): Command {
        return Commands.runOnce({setPosition(ELEVATOR_HEIGHTS.GREEN_LOW.position)})
    }

    fun goTofoothiles(): Command {
        return Commands.runOnce({setPosition(ELEVATOR_HEIGHTS.FOOTHILES_LOW.position)})
    }

    fun goToHumanPlayer_disk(): Command {
        return Commands.runOnce({setPosition(ELEVATOR_HEIGHTS.HUMANPLAYER_DISK.position)})
    }

    override fun simulationPeriodic() {
        MainMotor.updateInputs()
        Logger.processInputs(name, MainMotor.inputs)
        Logger.recordOutput("elevator", name)
    }
}