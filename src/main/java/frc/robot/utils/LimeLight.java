package frc.robot.utils;

public class LimeLight {
    public static void main(String[] args) {
        int i = 0;

        // thread 1
        i = i + 1;

        // thread 2
        i = i + 1;
    }
}
