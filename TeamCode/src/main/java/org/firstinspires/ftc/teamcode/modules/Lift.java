package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.configurations.HardwareNames;
import org.firstinspires.ftc.teamcode.configurations.LiftConfig;
import org.firstinspires.ftc.teamcode.utils.MultipleTelemetry;
import org.firstinspires.ftc.teamcode.utils.PID;

public class Lift extends Module {
  DcMotor leftMotor, rightMotor;
  PID regulator;
  private double targetPosition;
  
  public Lift(OpMode opMode, MultipleTelemetry telemetry) {
    super(opMode, telemetry);
  }
  
  @Override
  public void init() {
    leftMotor = opMode.hardwareMap.get(DcMotor.class, HardwareNames.leftLiftMotor);
    rightMotor = opMode.hardwareMap.get(DcMotor.class, HardwareNames.rightLiftMotor);//selected as master
    
    leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    
    leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);//TODO check direction
    
    regulator = new PID(LiftConfig.coefficients);
  }
  
  @Override
  public void start() {
    leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    
    targetPosition = rightMotor.getCurrentPosition();
    regulator.reset();
  }
  
  @Override
  public void loop() {
    double power = regulator.calculate(rightMotor.getCurrentPosition(), targetPosition);
    leftMotor.setPower(power);
    rightMotor.setPower(power);
  }
  
  @Override
  public void addTelemetry() {
  
  }
  
  @Override
  public void addAdvancedTelemetry() {
    telemetry.addData("Lift current pos:", rightMotor.getCurrentPosition());
    telemetry.addData("Lift target pos:", targetPosition);
  }
  
}
