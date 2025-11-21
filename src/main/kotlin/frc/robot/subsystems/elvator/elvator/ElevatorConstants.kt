package frc.robot.subsystems.elvator.elvator

import com.ctre.phoenix6.configs.CurrentLimitsConfigs
import com.ctre.phoenix6.configs.MotorOutputConfigs
import com.ctre.phoenix6.configs.Slot0Configs
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import edu.wpi.first.units.measure.Distance
import frc.robot.lib.extensions.meters
import frc.robot.lib.extensions.millimeters

val MOTORPORT=0
val GEARRATIO=0.0                                   //TODO: change this
val SPORCKET_DIAMETER : Distance=36.4.millimeters  //TODO: change this

val MOTORCONFIG=
    TalonFXConfiguration().apply {
        MotorOutput=
            MotorOutputConfigs().apply {
            NeutralMode= NeutralModeValue.Brake
            Inverted= InvertedValue.CounterClockwise_Positive
            }
     CurrentLimits=
         CurrentLimitsConfigs().apply {
             StatorCurrentLimit=70.0
             SupplyCurrentLimit=70.0
             StatorCurrentLimitEnable=true
             SupplyCurrentLimitEnable=true
         }
        Slot0=
            Slot0Configs().apply {
                kP=1.0
            }
    }

enum class ELEVATOR_HIGHTS(val position: Distance){
    FOOTHILES_LOW (0.0.meters),
    PURPLE_LOW (868.222.millimeters),
    GREEN_LOW (603.35.millimeters),
    HUMANPLAYER_DISK (628.65.millimeters),

}