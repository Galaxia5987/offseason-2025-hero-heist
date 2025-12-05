package frc.robot.subsystems.shooter.flywheel

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.Slot0Configs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import frc.robot.lib.extensions.volts
import org.apache.commons.collections.functors.TruePredicate

val PORT = 12
val SHOOT_VOLTAGE = 8.volts
val STOP_VOLTAGE = 0.volts
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
    Slot0 =
        Slot0Configs().apply {
            kP = 1.0
            kD = 0.0
        }
}

