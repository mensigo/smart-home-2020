package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;

public class DoorCloseByIdAction implements Action {
    private final String objectId;
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public DoorCloseByIdAction(String objectId, boolean isQuiet, CommandSender commandSender) {
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
            String placeName = (door.getPlace() instanceof Room) ? ((Room) door.getPlace()).getName() : null;
            if (door.isOpen()) {
                door.setOpen(false);
                if (!isQuiet) {
                    System.out.println("Door " + door.getId() + " in place " + placeName + " has been closed.");
                }
            } else {
                if (!isQuiet) {
                    System.out.println("Door " + door.getId() + " in place " + placeName + " has been already closed.");
                }
            }
        }
    }
}
