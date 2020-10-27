package ru.sbt.mipt.oop.lib.coolcompany;

import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;

public interface SensorEventsManagerAdapter {
    void registerEventHandler(EventHandler handler);
    void start();
}
