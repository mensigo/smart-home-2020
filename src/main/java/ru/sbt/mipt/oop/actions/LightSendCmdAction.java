package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SensorCommandType;
import ru.sbt.mipt.oop.objects.Light;

public class LightSendCmdAction implements Action {
    private final CommandSender commandSender;
    private final SensorCommandType command;

    public LightSendCmdAction(SensorCommandType command, CommandSender commandSender) {
        this.commandSender = commandSender;
        this.command = command;
    }

    @Override
    public void act(Object object) {
        if (!(object instanceof Light)) {
            return;
        }
        Light light = (Light) object;
        SensorCommand sensorCommand = new SensorCommand(command, light.getId());
        commandSender.sendCommand(sensorCommand);
    }
}
