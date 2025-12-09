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
import frc.robot.subsystems.elvator.elvator.GEAR_RATIO
import org.littletonrobotics.junction.Logger
import org.team5987.annotation.LoggedOutput

object elevatorWrist: SubsystemBase() {
   private val MainMotor = UniversalTalonFX(
        MOTOR_PORT,
        gearRatio = GREAR_RATIO,
        config = MOTOR_CONFIG,
        momentOfInertia = 0.5.kilogramSquareMeters
    )

    val setpoint = 0.radians
    val positionRequest= PositionVoltage(0.0.radians)
    fun setAngle(angles: WristElevatorAngles): Command{
        return Commands.runOnce({MainMotor.setControl(positionRequest.withPosition(angles.angle)) })
    }

    val voltageRequest = VoltageOut(0.0)
    fun setVoltage(voltage: Voltage): Command {
        return Commands.runOnce({MainMotor.setControl(voltageRequest.withOutput(voltage))})
    }
    fun goTo_FOOTHILES_MID(): Command{
       return Commands.runOnce( {setAngle(WristElevatorAngles.FOOTHILES_MID)})
    }
    fun go_To_FOOTHILES_HIGH(): Command{
       return setAngle(WristElevatorAngles.FOOTHILES_HIGH)
    }
    fun go_To_PURPLE_HIGH(): Command{
      return setAngle(WristElevatorAngles.PURPLE_HIGH)
    }

    fun go_To_GREEN_HIGH(): Command{
        return setAngle(WristElevatorAngles.GREEN_HIGH)
    }

    override fun periodic() {
        MainMotor.updateInputs()
        Logger.processInputs(name, MainMotor.inputs)
        Logger.recordOutput("elevatorWrist", name)

    }

}