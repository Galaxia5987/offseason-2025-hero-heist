package frc.robot.subsystems.elvator.ElevatorWrist

import com.ctre.phoenix6.controls.PositionVoltage
import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.kilogramSquareMeters
import frc.robot.lib.extensions.radians
import frc.robot.lib.extensions.volts
import frc.robot.lib.universal_motor.UniversalTalonFX
import frc.robot.subsystems.elvator.elvator.Elevator
import frc.robot.subsystems.elvator.elvator.GEARRATIO
import org.team5987.annotation.LoggedOutput

object ElevatorWrist: SubsystemBase() {
    val MainMotor = UniversalTalonFX(
        MOTORPORT,
        gearRatio = GEARRATIO,
        config = MOTORCONFIG,
        momentOfInertia = 0.5.kilogramSquareMeters
    )

    val setpoint = 0.radians
    val positionRequest= PositionVoltage(0.0.radians)
    fun setAngle(angles: WristElevator_Angles): Command{
        return Commands.runOnce({MainMotor.setControl(positionRequest.withPosition(angles.angle)) })
    }

    val voltageRequest = VoltageOut(0.0)
    fun setVoltage(voltage: Voltage): Command {
        return Commands.runOnce({MainMotor.setControl(voltageRequest.withOutput(voltage))})
    }
    fun GoTo_FOOTHILES_MID(): Command{
       return Commands.runOnce( {setAngle(WristElevator_Angles.FOOTHILES_MID)})
    }
    fun Go_To_FOOTHILES_HIGH(): Command{
       return Commands.runOnce({setAngle(WristElevator_Angles.FOOTHILES_HIGH)})
    }
    fun Go_To_PURPLE_HIGH(): Command{
      return Commands.runOnce({setAngle(WristElevator_Angles.PURPLE_HIGH)})
    }

    fun Go_To_GREEN_HIGH(): Command{
        return Commands.runOnce({setAngle(WristElevator_Angles.GREEN_HIGH)})
    }

    override fun periodic() {
        MainMotor.inputs
    }
}