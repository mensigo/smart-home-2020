package ru.sbt.mipt.oop.commandactions;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SensorCommandType;
import ru.sbt.mipt.oop.objects.LightActionable;

public class TurnOffLightCommandAction implements CommandAction {

    @Override
    public void executeAction(Object object) {
        if (!(object instanceof LightActionable)) {
            return;
        }
        LightActionable light = (LightActionable) object;
        SensorCommand command = new SensorCommand(SensorCommandType.LIGHT_OFF, light.getId());
        sendCommand(command);
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
