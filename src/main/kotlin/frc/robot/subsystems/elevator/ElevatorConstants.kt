package frc.robot.subsystems.elevator

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.Slot0Configs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import edu.wpi.first.units.measure.Distance
import frc.robot.lib.Gains
import frc.robot.lib.extensions.meters

val GEAR_RATIO=1.0
val DIAMETER= 0.3.meters
val REAL_GAINS = Gains(kP = 1.0 , kD = 0.1)
val SIM_GAINS = Gains(kP = 1.0 , kD = 0.1)
val PORT =4
val CONFIG = TalonFXConfiguration().apply {
    Slot0= Slot0Configs().apply {
        kP = REAL_GAINS.kP
        kD= REAL_GAINS.kD
    }
    MotorOutput = MotorOutputConfigs().apply {
        NeutralMode = NeutralModeValue.Brake
        Inverted = InvertedValue.Clockwise_Positive
    }
    CurrentLimits = CurrentLimitsConfigs().apply {
        StatorCurrentLimit = 20.0
        SupplyCurrentLimit = 10.0
        StatorCurrentLimitEnable = true
        SupplyCurrentLimitEnable = true
    }
}
enum class LEVELS(val position: Distance){
    LEVEL1   (0.5.meters),
    LEVEL2   (1.meters),
    LEVEL3   (2.meters),
    LEVEL4   (2.5.meters)
}
