// Definir as configurações de 4 controladores de motor
// ID's = 14(front), 15(follow), 17(front), 16(follow)

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class DriveTrainSubsystem extends SubsystemBase {
 //Variáveis privadas dos controladores de motor
  private SparkMax frontLeft; //14
  private SparkMax backLeft;  //15
  private SparkMax frontRight;  //17
  private SparkMax backRight;   //16
 
 //Variáveis para a configuração de cada SparkMax
  private SparkMaxConfig frontLeftConfig;
  private SparkMaxConfig backLeftConfig;
  private SparkMaxConfig frontRightConfig;
  private SparkMaxConfig backRightConfig;

  private DifferentialDrive drive;
  

  public DriveTrainSubsystem() {

    frontLeft = new SparkMax(14, MotorType.kBrushless);
    backLeft = new SparkMax(15, MotorType.kBrushless);
    frontRight = new SparkMax(17, MotorType.kBrushless);
    backRight = new SparkMax(16, MotorType.kBrushless);

    drive = new DifferentialDrive(frontLeft, frontRight);
    drive.setSafetyEnabled(false);


    frontLeftConfig = new SparkMaxConfig();

    frontLeftConfig
    .smartCurrentLimit(40, 60)
    .idleMode(IdleMode.kBrake)
    .inverted(true);


    backLeftConfig = new SparkMaxConfig();

    backLeftConfig
    .smartCurrentLimit(40, 60)
    .idleMode(IdleMode.kBrake)
    .inverted(true)
    .follow(frontLeft);


    frontRightConfig = new SparkMaxConfig();

    frontRightConfig
    .smartCurrentLimit(40, 60)
    .idleMode(IdleMode.kBrake)
    .inverted(false);


    backRightConfig = new SparkMaxConfig();

    backRightConfig
    .smartCurrentLimit(40, 60)
    .idleMode(IdleMode.kBrake)
    .inverted(false)
    .follow(frontRight);

    frontLeft.configure(frontLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    frontRight.configure(frontRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    backLeft.configure(backLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    backRight.configure(backRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }

  
  public void stopMotor() {
    frontLeft.stopMotor();
    frontRight.stopMotor();
  }
  
  public void maxOutput(Class<Double> maxOutput) {
    drive.setMaxOutput(RobotContainer.getXboxController().getLeftTriggerAxis());
  }
  
}
