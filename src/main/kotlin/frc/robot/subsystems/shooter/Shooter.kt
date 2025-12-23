package frc.robot.subsystems.shooter

import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.volts
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.littletonrobotics.junction.Logger
import org.team5987.annotation.LoggedOutput

object Shooter : SubsystemBase(){

  private  val motor = UniversalTalonFX(port = PORT , simGains = SIM_GAINS, config = CONFIG);
  @LoggedOutput  var voltageSetpoint= 0.volts
private val voltageRequest : VoltageOut= VoltageOut(0.0)

    fun setVoltage(voltage: Voltage ): Command= runOnce {
    voltageSetpoint = voltage;
    motor.setControl(voltageRequest.withOutput(voltage))
    }

    fun shoot(): Command = setVoltage(SHOOT_VOLTAGE)
    fun stop(): Command = setVoltage(0.volts)

    override fun periodic() {
        Logger.processInputs("Shooter",motor.inputs)
        Logger.recordOutput("Shooter/targetVoltage", Shooter.voltageSetpoint)
    }
}