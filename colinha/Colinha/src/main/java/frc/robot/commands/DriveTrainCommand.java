package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveTrainCommand extends Command {
  
  private final DriveTrainSubsystem tank;

  /** Creates a new DriveTrainCommand. */
  public DriveTrainCommand(DriveTrainSubsystem driveTrainSubsystem) {
    this.tank = driveTrainSubsystem;
    addRequirements(tank);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xSpeed = RobotContainer.getXboxController().getLeftY();
    double zRotation = RobotContainer.getXboxController().getLeftX();

    tank.arcadeDrive(xSpeed, zRotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    tank.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
