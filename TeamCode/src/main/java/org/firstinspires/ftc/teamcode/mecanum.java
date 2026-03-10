package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.hardware.tração;

public class mecanum extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();

    tração hard = new tração(this);

    double x, y, rx;


    private double[] motorPower = new double[4];

    // Variável que guarda o ângulo do robô (radianos)
    double angle;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Inicia o hardware do robô
        hard.hardwareGeral();

        runtime.reset();

        waitForStart();

        while (opModeIsActive()) {
            /*
             * drive controla a translação do robô (frente e trás)
             * x controla a translação do robô (esquerda e direita)
             * rx controla a rotação do robô
             */
            y = -gamepad1.left_stick_y; // Negativo por causa do gamepad
            x = gamepad1.left_stick_x * 1.2; // Multiplicamos por um valor para melhorar o strafe
            rx = gamepad1.right_stick_x;

            /*
             * Calculo para orientação no campo
             * caso queira desativar, apenas apague ou comente a linha abaixo
             */
            fieldOriented(y, x);

            // Valores para movimentação com mecanum (lados espelhados)
            //Motor Esquerda Frente;
            motorPower[0] = y + x + rx;
            // Motor Esquerda trás;
            motorPower[1] = y - x + rx;
            // Motor Direita Frente;
            motorPower[2] = y - x - rx;
            // Motor Direita trás;
            motorPower[3] = y + x - rx;

            // Verificar se algum valor é maior que 1
            if (Math.abs(motorPower[0]) > 1 || Math.abs(motorPower[1]) > 1
                    || Math.abs(motorPower[2]) > 1 || Math.abs(motorPower[3]) > 1) {

                //Achar o maior valor
                double max;
                max = Math.max(Math.abs(motorPower[0]), Math.abs(motorPower[1]));
                max = Math.max(Math.abs(motorPower[2]), max);
                max = Math.max(Math.abs(motorPower[3]), max);

                //Não ultrapassar +/-1 (proporção);
                motorPower[0] /= max;
                motorPower[1] /= max;
                motorPower[2] /= max;
                motorPower[3] /= max;
            }

            // Metodo setPower que manda a potência para os motores.
            hard.frontLeft.setPower(motorPower[0]);
            hard.backLeft.setPower(motorPower[1]);
            hard.frontRight.setPower(motorPower[2]);
            hard.backRight.setPower(motorPower[3]);
            if(gamepad1.a) {
                hard.imu.resetYaw();
            }
            // Telemetria com os valores de cada roda
            telemetry.addData("Motor Esquerdo %.2f ", motorPower[0]);
            telemetry.addData("Motor EsquerdoTras %.2f ", motorPower[1]);
            telemetry.addData("Motor Direita %.2f ", motorPower[2]);
            telemetry.addData("Motor DireitaTras %.2f ", motorPower[3]);
            telemetry.addData("Orientação %.2f ", Math.toDegrees(angle));
            telemetry.addData("Tempo passado ", runtime.toString());
            telemetry.update();
        }
    }

    // Função que calcula a orientação no campo
    private void fieldOriented(double yP, double xP) {
        angle = gyroCalculate();
        y = yP * Math.cos(angle) - xP * Math.sin(angle);
        x = yP * Math.sin(angle) + xP * Math.cos(angle);
    }

    // Função que retorna a orientação do robô em graus
    private double gyroCalculate() {
        YawPitchRollAngles orientation = hard.imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.RADIANS);
    }
}