package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;

public class DoorCloseInHallAction implements Action {
    private final String objectId;
    private final CommandSender commandSender;
    private final boolean isQuiet;
    private SmartHome smartHome;

    public DoorCloseInHallAction(String objectId, boolean isQuiet, CommandSender commandSender) {
        this.objectId = objectId;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void act(Object object) {
        if (object instanceof SmartHome) {
            smartHome = (SmartHome) object;
            return;
        }
        if (object instanceof Door) {
            Door door = ((Door) object);
            if (door.getId().equals(objectId) && door.isOpen()
                    && door.getPlaceName().equalsIgnoreCase("hall")) {
                smartHome.execute(new LightOffSendCmdAction(isQuiet, commandSender));
            }
        }
    }
}
