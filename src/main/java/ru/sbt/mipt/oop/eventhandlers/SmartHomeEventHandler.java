package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class SmartHomeEventHandler implements SmartObjectEventHandler {
    @Override
    public void handleEvent(SensorEvent event, Object object) {
        if (!(object instanceof SmartHome)) {
            throw new RuntimeException("RoomSmartObjectEventHandler::handleEvent(..) param @object is not instanceof SmartHome.");
        }
        for (Room room : ((SmartHome) object).getRooms()) {
            room.handleUsualEvent(event);
        }
    }
}
