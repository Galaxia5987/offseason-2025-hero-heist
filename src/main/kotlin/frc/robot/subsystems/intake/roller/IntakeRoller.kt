package frc.robot.subsystems.intake.roller

import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.namedRunOnce
import frc.robot.lib.universal_motor.UniversalTalonFX
import frc.robot.subsystems.intake.wrist.IntakeWrist
import frc.robot.subsystems.intake.wrist.PORT

object IntakeRoller : SubsystemBase() {
    val motor = UniversalTalonFX(
        PORT,
        config = MOTOR_CONFIG
    )
    val VoltageRequest = VoltageOut(0.0)

    fun setVoltage(voltage: Voltage): Command = namedRunOnce {
        motor.setControl(VoltageRequest.withOutput(voltage))
    }

    override fun periodic() = motor.processInputs()
}
