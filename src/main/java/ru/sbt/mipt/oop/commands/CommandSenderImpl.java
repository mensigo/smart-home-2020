package ru.sbt.mipt.oop.commands;

public class CommandSenderImpl implements CommandSender {
    @Override
    public void sendCommand(SensorCommand sensorCommand) {
        System.out.println("Sending command.. " + sensorCommand);
    }
}
