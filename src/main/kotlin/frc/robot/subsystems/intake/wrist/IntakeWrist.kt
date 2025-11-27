package frc.robot.subsystems.intake.wrist

import com.ctre.phoenix6.controls.PositionVoltage
import edu.wpi.first.units.measure.Angle
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj2.command.button.Trigger
import frc.robot.lib.extensions.deg
import frc.robot.lib.extensions.get
import frc.robot.lib.extensions.kg2m
import frc.robot.lib.namedRunOnce
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.littletonrobotics.junction.mechanism.LoggedMechanism2d
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d
import org.team5987.annotation.LoggedOutput

object IntakeWrist : SubsystemBase() {
    private val motor =
        UniversalTalonFX(
            PORT,
            simGains = SIM_GAINS,
            momentOfInertia = 0.0025.kg2m,
            config = MOTOR_CONFIG
        )
    private val positionRequest = PositionVoltage(0.0)
    @LoggedOutput var mechanism = LoggedMechanism2d(6.0, 4.0)
    private var root = mechanism.getRoot("Wrist", 3.0, 2.0)
    private val ligament =
        root.append(LoggedMechanismLigament2d("WristLigament", 0.25, 90.0))

    @LoggedOutput var setpoint = 0.deg

    @LoggedOutput
    val atSetPoint = Trigger {
        motor.inputs.position.isNear(setpoint, INTAKE_WRIST_TOLERANCE)
    }
    fun setAngle(angle: Angle): Command = namedRunOnce {
        setpoint = angle
        ligament.setAngle(angle[deg])
        motor.setControl(positionRequest.withPosition(angle))
    }

    override fun periodic() = motor.processInputs()
}
