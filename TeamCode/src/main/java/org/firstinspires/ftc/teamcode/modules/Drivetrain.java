package org.firstinspires.ftc.teamcode.modules;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.configurations.DrivetrainConfig;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.utils.Alliance;
import org.firstinspires.ftc.teamcode.utils.MultipleTelemetry;

public class Drivetrain extends Module {
  private Follower follower;
  Alliance alliance;
  
  public Drivetrain(OpMode opMode, MultipleTelemetry telemetry, Alliance alliance) {
    super(opMode, telemetry);
    this.alliance = alliance;
  }
  
  @Override
  public void init() {
    follower = Constants.createFollower(opMode.hardwareMap);
    follower.setStartingPose(alliance == Alliance.RED ? DrivetrainConfig.RED_START_POSE :
        DrivetrainConfig.BLUE_START_POSE);
  }
  
  @Override
  public void start() {
    follower.startTeleOpDrive();
  }
  
  @Override
  public void loop() {
    follower.update();
    follower.setTeleOpDrive(
        opMode.gamepad1.left_stick_y,
        opMode.gamepad1.left_stick_x,
        (opMode.gamepad1.right_trigger - opMode.gamepad1.left_trigger) * DrivetrainConfig.TURN_SLOWER_COF,
        false
    );
  }
  
  @Override
  public void addTelemetry() {
  
  }
  @Override
  public void addAdvancedTelemetry(){
    telemetry.addData("Robot x:", follower.getPose().getX());
    telemetry.addData("Robot y:", follower.getPose().getY());
    telemetry.addData("Robot heading:",Math.toDegrees(follower.getPose().getHeading()));
  }
}
