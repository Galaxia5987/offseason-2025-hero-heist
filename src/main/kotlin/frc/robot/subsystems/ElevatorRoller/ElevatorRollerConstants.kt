package frc.robot.subsystems.ElevatorRoller

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.Slot0Configs
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import edu.wpi.first.units.measure.Voltage
import frc.robot.lib.extensions.volts
import javax.print.attribute.EnumSyntax


val MOTOR_PORT=0
val GEAR_RATIO=0.0
val MOTORCONFIG=
    TalonFXConfiguration().apply {
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

val RUN_VOLTAGE=5.volts
val STOP_VOLTAGE=0.volts

