package frc.robot.lib

import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers
import edu.wpi.first.wpilibj2.command.button.Trigger
import org.team5987.annotation.LoggedOutput

@LoggedOutput
val IS_RED: Boolean
    get() =
        DriverStation.getAlliance().isPresent &&
            DriverStation.getAlliance().get() == DriverStation.Alliance.Red
