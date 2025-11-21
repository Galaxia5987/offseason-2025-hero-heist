package frc.robot.subsystems.intake.wrist

import com.ctre.phoenix6.controls.PositionVoltage
import edu.wpi.first.units.measure.Angle
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.deg
import frc.robot.lib.namedRunOnce
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.team5987.annotation.LoggedOutput

object IntakeWrist : SubsystemBase() {
    var motor = UniversalTalonFX(PORT, config = MOTOR_CONFIG)
    var positionRequest = PositionVoltage(0.0)
    @LoggedOutput var setPoint = 0.deg

    fun setAngle(angle: Angle): Command = namedRunOnce {
        setPoint = angle
        motor.setControl(positionRequest.withPosition(angle))
    }
}
