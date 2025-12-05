package frc.robot.subsystems.shooter.conveyor

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.Slot0Configs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import frc.robot.lib.Gains

val PORT= 6
val GEAR_RATIO = 1.0/3.0
val SIM_GAINS = Gains(kP = 1.0 , kD = 0.2 )
val REAL_GAINS = Gains(kP = 1.0 , kD = 0.2)
val CONFIG =  TalonFXConfiguration().apply {

    MotorOutput = MotorOutputConfigs().apply {
        Inverted= InvertedValue.Clockwise_Positive
        NeutralMode = NeutralModeValue.Brake
    }

    CurrentLimits = CurrentLimitsConfigs().apply {
        SupplyCurrentLimitEnable = true
        StatorCurrentLimitEnable = true
        SupplyCurrentLimit = 20.0
        StatorCurrentLimit = 10.0
    }

    Slot0 = Slot0Configs().apply {
        kP= REAL_GAINS.kP
        kD = REAL_GAINS.kD
    }
}