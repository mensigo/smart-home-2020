package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.actions.CloseDoorAction;
import ru.sbt.mipt.oop.actions.OpenDoorAction;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.DoorActionable;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class SmartDoorEventHandler implements SmartObjectEventHandler {

    @Override
    public void handleEvent(SensorEvent event, Object object) {
        if (!(object instanceof DoorActionable)) {
            throw new RuntimeException("SmartDoorEventHandler::handleEvent(..) param @object is not instanceof DoorActionable.");
        }
        DoorActionable door = ((DoorActionable) object);
        if (event.getType() == DOOR_OPEN) {
            door.execute(new OpenDoorAction());
            if (!event.getQueit()) {
                System.out.println("Door " + door.getId() + " in room " + door.getPlaceName() + " was opened.");
            }
        } else {
            door.execute(new CloseDoorAction());
            if (!event.getQueit()) {
                System.out.println("Door " + door.getId() + " in room " + door.getPlaceName() + " was closed.");
            }
        }
    }
}
