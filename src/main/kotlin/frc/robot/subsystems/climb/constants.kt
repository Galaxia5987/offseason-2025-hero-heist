package frc.robot.subsystems.climb

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import frc.robot.lib.Gains
import frc.robot.lib.extensions.meters
import frc.robot.lib.extensions.mm

val climbPort = 0

val climbConfig = TalonFXConfiguration().apply {
    MotorOutputConfigs().apply {
        NeutralMode = NeutralModeValue.Brake
        Inverted = InvertedValue.Clockwise_Positive
    }
    CurrentLimits =
        CurrentLimitsConfigs().apply {
            SupplyCurrentLimitEnable = true
            SupplyCurrentLimit = 40.0
            StatorCurrentLimitEnable = true
            StatorCurrentLimit = 20.0
        }
}

val pid = Gains(kP = 1.0)


val diameter = 30.mm

val gearRatio = 1 / 20.54

val positionUp = 1.0.meters

val positionDown = 0.0.meters