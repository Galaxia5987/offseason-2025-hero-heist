package frc.robot.subsystems.gripper

import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.volts
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.littletonrobotics.junction.Logger
import org.team5987.annotation.LoggedOutput

object Gripper: SubsystemBase() {
    private val motor = UniversalTalonFX(port = PORT, simGains = SIM_GAINS , config = CONFIG)
   @LoggedOutput var voltageSetpoint = 0.volts
    private val voltageRequest= VoltageOut(0.volts)

    fun setVoltage(voltage: Voltage) = runOnce {
        voltageSetpoint=voltage
        motor.setControl(voltageRequest.withOutput(voltage))
    }

    fun grab(): Command= setVoltage(VOLTAGE_START)
    fun stop(): Command= setVoltage(0.volts)
    fun release(): Command= setVoltage(VOLTAGE_REVERSE)

    override fun periodic() {
        Logger.processInputs("Gripper",motor.inputs)
        Logger.recordOutput("Gripper/targetVoltage", Gripper.voltageSetpoint)
    }
}