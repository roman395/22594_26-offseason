package org.firstinspires.ftc.teamcode.utils;

import com.pedropathing.control.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {
  private final PIDFCoefficients coefficients;
  private double sumError, lastError, lastTime;
  private final ElapsedTime timer = new ElapsedTime();
  
  public PID(PIDFCoefficients coefficients) {
    this.coefficients = coefficients;
  }
  
  public PID setP(double value) {
    coefficients.P = value;
    return this;
  }
  
  public PID setI(double value) {
    coefficients.I = value;
    return this;
  }
  
  public PID setD(double value) {
    coefficients.D = value;
    return this;
  }
  
  public PID setF(double value) {
    coefficients.F = value;
    return this;
  }
  
  public PID reset() {
    sumError = 0;
    lastTime = 0;
    lastError = 0;
    timer.reset();
    return this;
  }
  
  public double calculate(double current, double target) {
    double currentTime = timer.milliseconds();
    double deltaTime = (lastTime == 0) ? 0 : (currentTime - lastTime);
    
    double error = target - current;
    
    double P = coefficients.P * error;
    
    if (deltaTime > 0) {
      sumError += error * deltaTime;
    }
    double I = coefficients.I * sumError;
    
    double derivative = (deltaTime > 0) ? (error - lastError) / deltaTime : 0;
    double D = coefficients.D * derivative;
    
    double F = coefficients.F * target;
    
    lastError = error;
    lastTime = currentTime;
    
    return P + I + D + F;
  }
  
}
