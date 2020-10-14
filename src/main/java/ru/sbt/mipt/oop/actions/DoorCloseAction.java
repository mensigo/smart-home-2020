package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;

public class DoorCloseAction implements Action {
    private final String objectId;
    private final CommandSender commandSender;
    private final boolean isQuiet;
    private SmartHome smartHome;

    public DoorCloseAction(String objectId, boolean isQuiet, CommandSender commandSender) {
        this.objectId = objectId;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void act(Object object) {
        if (object instanceof SmartHome) {
            // for DoorInHall action
            smartHome = (SmartHome) object;
            return;
        }
        if (!(object instanceof Door)) {
            return;
        }
        Door door = (Door) object;
        if (door.getId().equals(objectId)) {
            if (door.isOpen()) {
                if (door.getPlaceName().equalsIgnoreCase("hall")) {
                    // run DoorInHall scenario if necessary
                    smartHome.execute(new DoorCloseInHallAction(objectId, isQuiet, commandSender));
                } else {
                    // usual door closing
                    door.setOpen(false);
                    if (!isQuiet) {
                        System.out.println("Door " + door.getId() + " in place " + door.getPlaceName() + " has been closed.");
                    }
                }
            } else {
                if (!isQuiet) {
                    System.out.println("Door " + door.getId() + " in place " + door.getPlaceName() + " has been already closed.");
                }
            }
        }
    }
}
