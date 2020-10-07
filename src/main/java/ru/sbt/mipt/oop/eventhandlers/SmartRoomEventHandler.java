package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.DoorActionable;
import ru.sbt.mipt.oop.objects.LightActionable;
import ru.sbt.mipt.oop.objects.RoomActionable;

public class SmartRoomEventHandler implements SmartObjectEventHandler {

    @Override
    public void handleEvent(SensorEvent event, Object object) {
        if (!(object instanceof RoomActionable)) {
            throw new RuntimeException("SmartRoomEventHandler::handleEvent(..) param @object is not instanceof RoomActionable.");
        }
        RoomActionable room = (RoomActionable) object;
        if (event.isLightUsualEvent()) {
            // events from LightActionable
            boolean existObject = false;
            for (LightActionable light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    light.handleUsualEvent(event);
                    existObject = true;
                    break;
                }
            }
            if (!existObject) {
                System.out.println("No Light " + event.getObjectId() + " in place " + room.getName());
            }
        }
        if (event.isDoorUsualEvent()) {
            // events from DoorActionable
            boolean existObject = false;
            for (DoorActionable door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    door.handleUsualEvent(event);
                    existObject = true;
                    break;
                }
            }
            if (!existObject) {
                System.out.println("No Door " + event.getObjectId() + " in place " + room.getName());
            }
        }
    }
}
