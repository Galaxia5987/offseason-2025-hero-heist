package frc.robot.subsystems.elvator.ElevatorWrist

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.Slot0Configs
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.configs.TalonFXConfigurator
import edu.wpi.first.units.measure.Angle
import edu.wpi.first.units.measure.Distance
import frc.robot.lib.extensions.millimeters
import frc.robot.lib.extensions.radians


val MOTORPORT=1
val GREAR_RATIO=0.0
val SPORCKET_DIAMETER : Distance= 0.0.millimeters

val MOTORCONFIG=
    TalonFXConfiguration().apply {
        Slot0 = Slot0Configs().apply { kP = 0.8 }
        CurrentLimits =
            CurrentLimitsConfigs().apply {
                StatorCurrentLimitEnable = true
                SupplyCurrentLimitEnable = true
                StatorCurrentLimit = 20.0
                SupplyCurrentLimit = 40.0
            }

        SoftwareLimitSwitch =
            SoftwareLimitSwitchConfigs().apply {
                ForwardSoftLimitEnable = true
                ForwardSoftLimitThreshold = 15.5
                ReverseSoftLimitEnable = true
            }
    }
    enum class WristElevator_Angles(val angle: Angle){
        INDEALING (0.0.radians),
        FOOTHILES_MID (15.0.radians),
        FOOTHILES_HIGH (30.0.radians),
        PURPLE_HIGH (25.0.radians),
        GREEN_HIGH (10.0.radians)
    }





