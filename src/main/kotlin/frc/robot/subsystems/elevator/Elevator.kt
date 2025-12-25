package frc.robot.subsystems.elevator

import com.ctre.phoenix6.controls.PositionVoltage
import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Distance
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.meters
import frc.robot.lib.extensions.toAngle
import frc.robot.lib.extensions.volts
import frc.robot.lib.universal_motor.UniversalTalonFX
import org.littletonrobotics.junction.Logger
import org.littletonrobotics.junction.mechanism.LoggedMechanism2d
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d
import org.team5987.annotation.LoggedOutput

object Elevator : SubsystemBase() {
@LoggedOutput (key= "Elevator/mechanism")

var mechanism = LoggedMechanism2d (4.0, 6.0 )
private var root = mechanism.getRoot("Elevator", 2.0 , 1.0)
    private val ligament = root.append(
        LoggedMechanismLigament2d ("Elevator",1.0,0.0)
    )

 private   val motor = UniversalTalonFX(port = PORT , config = CONFIG , simGains = SIM_GAINS )

  @LoggedOutput  var setpoint = 0.meters
    val voltageRequest= VoltageOut(0.volts)

    fun setVoltage(voltage: Voltage)= runOnce {
motor.setControl(voltageRequest.withOutput(voltage))
    }

    val positionVoltage = PositionVoltage(0.0)
    fun setPosition(position : Distance)= runOnce {    setpoint = position
        motor.setControl(positionVoltage.withPosition(position.toAngle(diameter = DIAMETER, gearRatio = GEAR_RATIO)))
    }


    fun setpoint1(): Command= setPosition(LEVELS.LEVEL1.position)
    fun setpoint2(): Command= setPosition(LEVELS.LEVEL2.position)
    fun setpoint3(): Command= setPosition(LEVELS.LEVEL3.position)
    fun setpoint4(): Command= setPosition(LEVELS.LEVEL4.position)

    override fun periodic() {
        Logger.processInputs("Elevator",motor.inputs)
        Logger.recordOutput("levels", setpoint)
    }
}