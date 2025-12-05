package frc.robot.subsystems.shooter.flywheel

import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.volts
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.team5987.annotation.LoggedOutput

object Flywheel : SubsystemBase(){
    val motor = UniversalTalonFX(
        port = PORT,
        config = CONFIG
    )

    val voltageRequest = VoltageOut(0.0.volts)

    private fun setVoltage(voltage: Voltage): Command {
        return Commands.runOnce({
            motor.setControl(voltageRequest.withOutput(voltage))
        })
    }

    fun shoot(): Command = setVoltage(SHOOT_VOLTAGE)

    fun stop(): Command = setVoltage(STOP_VOLTAGE)

}