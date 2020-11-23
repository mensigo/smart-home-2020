package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;

public class DoorCloseInHallAction implements Action {
    private final String hallName;
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public DoorCloseInHallAction(String hallName, boolean isQuiet, CommandSender commandSender) {
        this.hallName = hallName;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void act(Object object) {
        if (!(object instanceof Door)) {
            return;
        }
        Door door = (Door) object;
        if (door.isOpen()
                && door.getPlace() instanceof Room
                && ((Room) door.getPlace()).getName().equals(hallName)) {
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
