package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.RoomActionable;
import ru.sbt.mipt.oop.objects.SmartHomeActionable;

public class SmartHomeEventHandler implements SmartObjectEventHandler {
    @Override
    public void handleEvent(SensorEvent event, Object object) {
        if (!(object instanceof SmartHomeActionable)) {
            throw new RuntimeException("RoomSmartObjectEventHandler::handleEvent(..) param @object is not instanceof SmartHomeActionable.");
        }
        for (RoomActionable room : ((SmartHomeActionable) object).getRooms()) {
            room.handleUsualEvent(event);
        }
    }
}
