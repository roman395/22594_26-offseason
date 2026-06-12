package org.firstinspires.ftc.teamcode.utils;

import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MultipleTelemetry {
  private final Telemetry telemetry;
  public MultipleTelemetry(OpMode mode){
    telemetry = mode.telemetry;
  }
  public void addData(String caption, Object value){
    telemetry.addData(caption, value);
    PanelsTelemetry.INSTANCE.getFtcTelemetry().addData(caption, value);
  }
  public void addLine(String line){
    telemetry.addLine(line);
    PanelsTelemetry.INSTANCE.getFtcTelemetry().addLine(line);
  }
}
