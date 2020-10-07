package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.objects.DoorActionable;

public class OpenDoorAction implements Action {

    @Override
    public void executeAction(Object object) {
        if (!(object instanceof DoorActionable)) {
            return;
        }
        ((DoorActionable) object).setOpen(true);
    }
}
