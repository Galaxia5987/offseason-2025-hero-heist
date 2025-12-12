package frc.robot.subsystems.shooter.flywheel

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.Slot0Configs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import frc.robot.lib.Gains
import frc.robot.lib.extensions.rps
import frc.robot.lib.extensions.volts

val PORT = 12
val SHOOT_VELOCITY = 30.rps
val STOP_VELOCITY = 0.rps
val BACK_IN_VELOCITY = (-30).rps
val PID_GAINS = Gains(kP = 0.023, kD = 0.0)
val CONFIG = TalonFXConfiguration().apply {
    MotorOutput =
        MotorOutputConfigs().apply {
            NeutralMode = NeutralModeValue.Coast
            Inverted = InvertedValue.Clockwise_Positive
        }
    CurrentLimits =
        CurrentLimitsConfigs().apply {
            StatorCurrentLimitEnable = true
            StatorCurrentLimit = 30.0
            SupplyCurrentLimitEnable = true
            SupplyCurrentLimit = 60.0
        }
}

