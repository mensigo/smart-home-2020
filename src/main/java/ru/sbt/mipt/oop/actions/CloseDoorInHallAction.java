package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.objects.DoorActionable;

public class CloseDoorInHallAction implements Action {

    @Override
    public void executeAction(Object object) {
        if (!(object instanceof DoorActionable)) {
            return;
        }
        DoorActionable door = ((DoorActionable) object);
        if (door.getPlaceName().equalsIgnoreCase("hall")) {
            door.setOpen(false);
        }
    }
}
