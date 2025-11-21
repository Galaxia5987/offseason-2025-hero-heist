package frc.robot.subsystems.elvator.ElevatorWrist

import com.ctre.phoenix6.controls.VoltageOut
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.extensions.kilogramSquareMeters
import frc.robot.lib.extensions.radians
import frc.robot.lib.universal_motor.UniversalTalonFX
import frc.robot.subsystems.elvator.elvator.Elevator
import frc.robot.subsystems.elvator.elvator.GEARRATIO

object ElevatorWrist: SubsystemBase() {
    val MainMotor = UniversalTalonFX(
        MOTORPORT,
        gearRatio = GEARRATIO,
        config = MOTORCONFIG,
        momentOfInertia = 0.5.kilogramSquareMeters
    )
    val setpoint = 0.radians

    val voltageRequest = VoltageOut(0.0)
    fun setVoltage(voltage: Voltage): Command {
        return Commands.runOnce({MainMotor.setControl(voltageRequest.withOutput(voltage))})
    }



}