package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.rr.TankDrive;

public class testerr extends LinearOpMode {
    @Override
    public void runOpMode(){
        Pose2d startPose = new Pose2d(0, 0, 0);
        TankDrive drive = new TankDrive(hardwareMap, startPose);

        waitForStart();

    }
}
