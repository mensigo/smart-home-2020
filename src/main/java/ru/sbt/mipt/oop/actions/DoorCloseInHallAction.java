package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.commands.SensorCommandType.LIGHT_OFF;

public class DoorCloseInHallAction implements Action {
    private final String objectId;
    private final String hallName;
    private final boolean isQuiet;
    private final CommandSender commandSender;
    private SmartHome smartHome;

    public DoorCloseInHallAction(String objectId, String hallName, boolean isQuiet, CommandSender commandSender) {
        this.objectId = objectId;
        this.hallName = hallName;
        this.isQuiet = isQuiet;
        this.commandSender = commandSender;
    }

    @Override
    public void act(Object object) {
        if (object instanceof SmartHome) {
            smartHome = (SmartHome) object;
            return;
        }
        if (object instanceof Door) {
            Door door = ((Door) object);
            if (door.getId().equals(objectId)
                    && door.getPlace() instanceof Room
                    && ((Room) door.getPlace()).getName().equals(hallName)) {
                smartHome.execute(new LightOffAction(isQuiet, commandSender));
                smartHome.execute(new LightSendCmdAction(LIGHT_OFF, commandSender));
            }
        }
    }
}
