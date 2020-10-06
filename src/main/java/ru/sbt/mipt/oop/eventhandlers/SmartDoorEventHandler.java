package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.Door;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class SmartDoorEventHandler implements SmartObjectEventHandler {

    @Override
    public void handleEvent(SensorEvent event, Object object) {
        if (!(object instanceof Door)) {
            throw new RuntimeException("SmartDoorEventHandler::handleEvent(..) param @object is not instanceof Door.");
        }
        Door door = ((Door) object);
        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            if (!event.getQueit()) {
                System.out.println("Door " + door.getId() + " in room " + door.getPlaceName() + " was opened.");
            }
        } else {
            door.setOpen(false);
            if (!event.getQueit()) {
                System.out.println("Door " + door.getId() + " in room " + door.getPlaceName() + " was closed.");
            }
        }
    }
}
