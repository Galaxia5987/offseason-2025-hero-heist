package frc.robot.subsystems.ElevatorRoller

import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.configs.TalonFXConfigurator
import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import frc.robot.lib.extensions.volts
import frc.robot.lib.universal_motor.UniversalTalonFX

class ElevatorRoller {
    val MainMotor= UniversalTalonFX(
        0,
        gearRatio = GEAR_RATIO,
        config = MOTORCONFIG
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