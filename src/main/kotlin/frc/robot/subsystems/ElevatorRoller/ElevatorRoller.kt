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
    private var voltageRequest= VoltageOut(0.0.volts)

    fun setvoltage(voltage: Voltage): Command{
        return Commands.runOnce({MainMotor.setControl(voltageRequest.withOutput(voltage))})
    }
    fun Run (): Command{
        return setvoltage(RUN_VOLTAGE)
    }

    fun Stop(): Command{
        return setvoltage(STOP_VOLTAGE)
    }
}