package frc.robot.subsystems.shooter.conveyor

import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.lib.universal_motor.UniversalTalonFX

object Conveyor: SubsystemBase(){
    val motor = UniversalTalonFX()
}