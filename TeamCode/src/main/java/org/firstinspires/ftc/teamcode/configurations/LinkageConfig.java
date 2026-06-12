package org.firstinspires.ftc.teamcode.configurations;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.control.PIDFCoefficients;

@Configurable
public class LinkageConfig {
  public static PIDFCoefficients coefficients = new PIDFCoefficients(0,0,0,0);//TODO tune
}
