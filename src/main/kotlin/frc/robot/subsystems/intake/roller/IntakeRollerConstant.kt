package frc.robot.subsystems.intake.roller

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import edu.wpi.first.units.measure.Voltage
import frc.robot.lib.extensions.volts
import frc.robot.lib.named

const val MOTOR_PORT = 0

val MOTOR_CONFIG = TalonFXConfiguration()
    .apply {
        CurrentLimits.apply {
            CurrentLimits = CurrentLimitsConfigs().apply {
                StatorCurrentLimitEnable = true
                SupplyCurrentLimitEnable = true
                StatorCurrentLimit = 20.0
                SupplyCurrentLimit = 40.0
            }
        }
    }


enum class intakeRoller(val voltage: Voltage) {
    INTAKE(12.volts),
    GREEN_CITY_BLOCK(INTAKE.voltage),
    GREEN_MAIL(INTAKE.voltage),
    RED_MAIL(INTAKE.voltage),
    KNOB_FOOTHILL(INTAKE.voltage),
    UPPER_FOOTHILL(INTAKE.voltage),
    LOWER_FOOTHILL(INTAKE.voltage),
    FEEDER(INTAKE.voltage),
    RED_FOOTHILL(INTAKE.voltage),
    STOP(0.volts),
    OUTTAKE((-12).volts); //TODO I will probably need to change this

    operator fun invoke() =
        IntakeRoller.setVoltage(voltage).named("Intake/Roller/",name)
}