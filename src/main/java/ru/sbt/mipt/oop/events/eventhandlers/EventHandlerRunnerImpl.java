package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.List;

public class EventHandlerRunnerImpl implements EventHandlerRunner {
    private final List<EventHandler> eventHandlerList;

    public EventHandlerRunnerImpl(List<EventHandler> eventHandlerList) {
        this.eventHandlerList = eventHandlerList;
    }

    @Override
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
        for (EventHandler eventHandler : eventHandlerList) {
            eventHandler.handleEvent(event, smartHome);
        }
    }
}
