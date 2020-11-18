package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public interface EventHandler {
    void handleEvent(SensorEvent event, SmartHome smartHome);
}
