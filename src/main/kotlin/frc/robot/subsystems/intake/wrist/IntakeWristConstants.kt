package frc.robot.subsystems.intake.wrist

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import edu.wpi.first.units.measure.Angle
import frc.robot.lib.Gains
import frc.robot.lib.extensions.deg
import frc.robot.lib.named

const val PORT = 1
val INTAKE_WRIST_TOLERANCE = 2.deg
val REAL_GAINS = Gains(1.0)
val SIM_GAINS = Gains(1.0)
val MOTOR_CONFIG =
    TalonFXConfiguration().apply {
        Slot0 = REAL_GAINS.toSlotConfig()
        CurrentLimits =
            CurrentLimitsConfigs().apply {
                StatorCurrentLimitEnable = true
                SupplyCurrentLimitEnable = true
                StatorCurrentLimit = 20.0
                SupplyCurrentLimit = 40.0
            }
        MotorOutput =
            MotorOutputConfigs().apply {
                Inverted = InvertedValue.Clockwise_Positive
                NeutralMode = NeutralModeValue.Coast
            }
    }

enum class intakeWrist(val angle: Angle) {
    GREEN_CITY_BLOCK(0.deg),
    GREEN_MAIL(20.deg), // TODO ill need to change it
    RED_MAIL(20.deg),
    KNOB_FOOTHILL(20.deg),
    UPPER_FOOTHILL(30.deg),
    LOWER_FOOTHILL(10.deg),
    FEEDER(10.deg), // TODO ill need to change it
    RED_FOOTHILL(0.deg);

    operator fun invoke() =
        IntakeWrist.setAngle(angle).named("Intake/Wrist/", name)
}
