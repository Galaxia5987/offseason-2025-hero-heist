package frc.robot.subsystems.shooter.conveyor

import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.volts

import frc.robot.lib.logged_output.LoggedOutputManager.runOnce
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.littletonrobotics.junction.Logger
import org.team5987.annotation.LoggedOutput

object Conveyor : SubsystemBase() {
 private val motor =
     UniversalTalonFX(port = PORT , gearRatio = GEAR_RATIO, config = CONFIG , simGains = SIM_GAINS )
    @LoggedOutput var voltageRequest : VoltageOut = VoltageOut(0.0)

    fun setVoltage (voltage : Voltage) = runOnce {
        motor.setControl(voltageRequest.withOutput(voltage))
    }

    fun start(): Command = setVoltage(6.5.volts)
    fun reverse(): Command = setVoltage(-6.5.volts)
    fun stop(): Command = setVoltage(0.volts)

    override fun periodic () {
        motor.updateInputs()
        Logger.processInputs("Subsystems/shooter/conveyor", motor.inputs)
    }

}
