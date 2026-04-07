package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.rr.TankDrive;
@TeleOp ( name = "TankOdo", group = "Linear TesteOp")
public class tankOdo extends LinearOpMode {
    @Override
    public void runOpMode(){
        Pose2d startPose = new Pose2d(0, 0, 0);
        TankDrive drive = new TankDrive(hardwareMap, startPose);

        waitForStart();

        while(opModeIsActive()){

            double y = -gamepad1.left_stick_y;
            double x = -gamepad1.right_stick_x;

            drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(y,0),
                            x
                    )
            );

            drive.updatePoseEstimate();

            Pose2d newPose = drive.localizer.getPose();

            telemetry.addData("y",newPose.position.y * 2.54);
            telemetry.addData("x", newPose.position.x * 2.54);

            telemetry.update();

        }
    }
}
