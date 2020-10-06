package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;

public class SmartRoomEventHandler implements SmartObjectEventHandler {

    @Override
    public void handleEvent(SensorEvent event, Object object) {
        if (!(object instanceof Room)) {
            throw new RuntimeException("SmartRoomEventHandler::handleEvent(..) param @object is not instanceof Room.");
        }
        Room room = (Room) object;
        if (event.isLightUsualEvent()) {
            // events from Light
            boolean existObject = false;
            for (Light light : room.getLights()) {
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
            // events from Door
            boolean existObject = false;
            for (Door door : room.getDoors()) {
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
