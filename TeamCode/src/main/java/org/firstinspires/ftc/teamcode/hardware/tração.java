package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

public class tração {
    OpMode opMode;
    //Declaração dos motores
    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;

    // Objeto da interface de giroscópio
    public IMU imu;

    public tração(OpMode opMode) {
        this.opMode = opMode;
    }

    public void hardwareGeral() {

        imu = opMode.hardwareMap.get(IMU.class, "imu");

        // Orientação do Control Hub/Expansion Hub
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.DOWN;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu.initialize(new IMU.Parameters(orientationOnRobot));


        frontLeft = opMode.hardwareMap.get(DcMotor.class, "motor_esquerda");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "motor_esquerdatras");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "motor_direita");
        backRight = opMode.hardwareMap.get(DcMotor.class, "motor_direitatras");

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        imu.resetYaw();
    }
}
