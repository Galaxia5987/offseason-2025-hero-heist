package frc.robot.subsystems.ElevatorRoller

import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.configs.TalonFXConfigurator
import com.ctre.phoenix6.controls.VoltageOut
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.hardware.TalonFXS
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.volts
import frc.robot.lib.universal_motor.UniversalTalonFX
object elevatorRoller: SubsystemBase() {
    private val MainMotor= UniversalTalonFX(
        MOTOR_PORT,
        config = MOTOR_CONFIG
    )
    var voltageRequest= VoltageOut(0.0.volts)

    fun setVoltage(voltage: Voltage): Command{
        return Commands.runOnce({MainMotor.setControl(voltageRequest.withOutput(voltage))})
    }
    fun Run (): Command{
        return Commands.runOnce({(setVoltage(RUN_VOLTAGE))})
    }

    fun Stop(): Command{
        return Commands.runOnce({setVoltage(STOP_VOLTAGE)})
    }
}