package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface SmartObjectEventHandler {
    void handleEvent(SensorEvent event, Object object);
}
