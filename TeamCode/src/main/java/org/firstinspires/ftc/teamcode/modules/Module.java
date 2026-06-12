package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.utils.MultipleTelemetry;

public abstract class Module {
  protected OpMode opMode;
  protected MultipleTelemetry telemetry;
  public Module(OpMode opMode, MultipleTelemetry telemetry){
    this.opMode = opMode;
    this.telemetry = telemetry;
  }
  public abstract void init();
  public abstract void start();
  public abstract void loop();
  public abstract void addTelemetry();
  
}
