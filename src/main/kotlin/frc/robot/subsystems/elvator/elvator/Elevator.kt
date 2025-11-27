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

object Elevator: SubsystemBase() {
private val MainMotor=
    UniversalTalonFX(
        MOTORPORT,
        gearRatio = GEARRATIO,
        config = MOTORCONFIG
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
            position.toAngle(SPORCKET_DIAMETER, GEARRATIO)
        ))})
    }


    override fun periodic() {
        MainMotor.updateInputs()
        Logger.processInputs(name, MainMotor.inputs)
        Logger.recordOutput("levels", setPoint)
    }

   fun GoToPurple_low(): Command {
       return Commands.runOnce({setPosition(ELEVATOR_HIGHTS.PURPLE_LOW.position)})
   }

    fun GoToGreen_low(): Command {
        return Commands.runOnce({setPosition(ELEVATOR_HIGHTS.GREEN_LOW.position)})
    }

    fun GoTofoothiles(): Command {
        return Commands.runOnce({setPosition(ELEVATOR_HIGHTS.FOOTHILES_LOW.position)})
    }

    fun GoToHumanPlayer_disk(): Command {
        return Commands.runOnce({setPosition(ELEVATOR_HIGHTS.HUMANPLAYER_DISK.position)})
    }
}