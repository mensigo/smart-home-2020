package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Door;

public class DoorOpenByIdAction implements Action {
    private final String objectId;
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public DoorOpenByIdAction(String objectId, boolean isQuiet, CommandSender commandSender) {
        this.objectId = objectId;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void act(Object object) {
        if (!(object instanceof Door)) {
            return;
        }
        Door door = (Door) object;
        if (door.getId().equals(objectId)) {
            if (!door.isOpen()) {
                door.setOpen(true);
                if (!isQuiet) {
                    System.out.println("Door " + door.getId() + " in place " + door.getPlaceName() + " has been opened.");
                }
            } else {
                if (!isQuiet) {
                    System.out.println("Door " + door.getId() + " in place " + door.getPlaceName() + " has been already opened.");
                }
            }
        }
    }
}
