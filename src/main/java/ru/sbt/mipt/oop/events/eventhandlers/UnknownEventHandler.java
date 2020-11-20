package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.UNKNOWN_EVENT;

public class UnknownEventHandler implements EventHandler {
    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType().equals(UNKNOWN_EVENT)) {
            SensorEvent unknownEvent = event;
            SensorEventType type = unknownEvent.getType();
            String id = unknownEvent.getObjectId();
            System.out.println("Event type [" + type + "] from object with id=" + id);
        }
    }
}
