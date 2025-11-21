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

val INTAKE = (-12).volts
enum class RollerVoltages(val voltage: Voltage){
    GREEN_CITY_BLOCK(INTAKE),
    GREEN_MAIL(INTAKE),
    RED_MAIL(INTAKE),
    KNOB_FOOTHILL(INTAKE),
    UPPER_FOOTHILL(INTAKE),
    LOWER_FOOTHILL(INTAKE),
    FEEDER(INTAKE),
    RED_FOOTHILL(INTAKE),
    STOP(0.volts),
    OUTTAKE((-12).volts); //TODO I Probably need to change this?
    operator fun invoke() =
        IntakeRoller.setVoltage(voltage).named(commandName = "Roller_SET$name")
}