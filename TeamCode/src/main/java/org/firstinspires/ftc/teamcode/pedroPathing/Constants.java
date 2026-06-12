package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PredictiveBrakingCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.TwoWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.configurations.HardwareNames;

public class Constants {
  public static FollowerConstants followerConstants = new FollowerConstants()
      .mass(5)//TODO fill with right value
      .headingPIDFCoefficients(new PIDFCoefficients(0, 0, 0, 0))//TODO tune
      .translationalPIDFCoefficients(new PIDFCoefficients(0, 0, 0, 0))//TODO tune
      .drivePIDFCoefficients(new FilteredPIDFCoefficients(0, 0, 0, 0, 0))//TODO tune
      .useSecondaryDrivePIDF(false)
      .useSecondaryHeadingPIDF(false)
      .useSecondaryTranslationalPIDF(false)
      .forwardZeroPowerAcceleration(0)//TODO tune
      .lateralZeroPowerAcceleration(0)//TODO tune
      .centripetalScaling(0)
      .predictiveBrakingCoefficients(new PredictiveBrakingCoefficients(0, 0, 0)); //TODO tune
  public static MecanumConstants driveConstants = new MecanumConstants()
      .maxPower(1)
      .rightFrontMotorName(HardwareNames.frontRightMotor)
      .rightRearMotorName(HardwareNames.rearRightMotor)
      .leftFrontMotorName(HardwareNames.frontLeftMotor)
      .leftRearMotorName(HardwareNames.rearLeftMotor)
      .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
      .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
      .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
      .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)//TODO change if needs
      .xVelocity(0)//TODO tune
      .yVelocity(0);//Todo tune
  public static TwoWheelConstants localizerConstants = new TwoWheelConstants()
      .forwardEncoder_HardwareMapName(HardwareNames.forwardOdometry)
      .strafeEncoder_HardwareMapName(HardwareNames.strafeOdometry)
      .IMU_HardwareMapName(HardwareNames.imu)
      .IMU_Orientation(
          new RevHubOrientationOnRobot(
              RevHubOrientationOnRobot.LogoFacingDirection.UP,
              RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD//TODO change if needs
          )
      )
      .forwardPodY(0)//TODO measure or tune automatic
      .strafePodX(0);
  public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);
  
  public static Follower createFollower(HardwareMap hardwareMap) {
    return new FollowerBuilder(followerConstants, hardwareMap)
        .pathConstraints(pathConstraints)
        .mecanumDrivetrain(driveConstants)
        .twoWheelLocalizer(localizerConstants)
        .build();
  }
  
}
