package frc.robot.subsystems.intake.wrist

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import edu.wpi.first.units.measure.Angle
import frc.robot.lib.Gains
import frc.robot.lib.extensions.deg
import frc.robot.lib.named

const val PORT = 1

val Gains = Gains(1.0)
val MOTOR_CONFIG =
    TalonFXConfiguration().apply {
        Slot0 = Gains.toSlotConfig()
        CurrentLimits =
            CurrentLimitsConfigs().apply {
                StatorCurrentLimitEnable = true
                SupplyCurrentLimitEnable = true
                StatorCurrentLimit = 20.0
                SupplyCurrentLimit = 40.0
            }
    }

enum class WristAngles(val angle: Angle) {
    GREEN_CITY_BLOCK(0.deg),
    GREEN_MAIL(20.deg),
    RED_MAIL(20.deg),
    RED_FOOTHILL(0.deg),
    KNOB_FOOTHILL(20.deg),
    UPPER_FOOTHILL(30.deg),
    LOWER_FOOTHILL(10.deg),
    FEEDER(10.deg);

    operator fun invoke() = Wrist.setAngle(this.angle).named(commandName = "SET_${this.name}")
}
