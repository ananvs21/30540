package org.firstinspires.ftc.teamcode.rr;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


@Autonomous(name = "Basic Auto")
public class testeAuto extends LinearOpMode {
    @Override
    public void runOpMode() {

        Pose2d startPose = new Pose2d(0,0,0);
        TankDrive drive = new TankDrive(hardwareMap, startPose);

//        drive.updatePoseEstimate();
        waitForStart();

        Action trajectory = drive.actionBuilder(startPose)//posição inicial
                .lineToX(10 ) //mover 48 polegadas na direção y
//                .turn(Math.toRadians(90)) //rotacionar 90°
//                .lineToY(48) //mover 48 polegadas na direção X
                .build(); //"construção final", quando o caminho estiver pronto
        telemetry.addLine("Auto started");
        telemetry.update();


        while(opModeIsActive()) {
            Actions.runBlocking(trajectory); //executa a ação
            break;
        }
    }
}