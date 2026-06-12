package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.configurations.HardwareNames;
import org.firstinspires.ftc.teamcode.configurations.LinkageConfig;
import org.firstinspires.ftc.teamcode.utils.MultipleTelemetry;
import org.firstinspires.ftc.teamcode.utils.PID;

public class Linkage extends Module {
  private DcMotor motor;
  private PID regulator;
  private double targetPosition;
  
  public Linkage(OpMode opMode, MultipleTelemetry telemetry) {
    super(opMode, telemetry);
  }
  
  @Override
  public void init() {
    motor = opMode.hardwareMap.get(DcMotor.class, HardwareNames.linkage);
    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    regulator = new PID(LinkageConfig.coefficients);
  }
  
  @Override
  public void start() {
    regulator.reset();
    motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    targetPosition = motor.getCurrentPosition();
  }
  
  @Override
  public void loop() {
    motor.setPower(regulator.calculate(motor.getCurrentPosition(), targetPosition));
  }
  
  @Override
  public void addTelemetry() {
  
  }
  
  @Override
  public void addAdvancedTelemetry() {
  
  }
  
}
