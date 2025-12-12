package frc.robot.subsystems.climb

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.Slot0Configs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import frc.robot.lib.Gains
import frc.robot.lib.extensions.m
import frc.robot.lib.extensions.meters

val PORT = 0

val CONFIG = TalonFXConfiguration().apply {
    MotorOutputConfigs().apply {
        NeutralMode = NeutralModeValue.Brake
        Inverted = InvertedValue.Clockwise_Positive
    }
    CurrentLimits =
        CurrentLimitsConfigs().apply {
            SupplyCurrentLimitEnable = true
            SupplyCurrentLimit = 80.0
            StatorCurrentLimitEnable = true
            StatorCurrentLimit = 40.0
        }
    Slot0 =
        Slot0Configs().apply {
            kP = 1.0
            kD = 0.0
        }
}

val PID_GAINS = Gains(kP = 0.45 , kD = 0.06)


val DIAMETER = 0.3.m

val GEAR_RATIO = 1 / 12.5

val POSITION_UP = 1.0.meters

val POSITION_DOWN = 0.0.meters