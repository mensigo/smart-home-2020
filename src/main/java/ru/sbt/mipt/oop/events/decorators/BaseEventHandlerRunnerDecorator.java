package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.objects.SmartHome;

public class BaseEventHandlerRunnerDecorator implements EventHandlerRunner {
    EventHandlerRunner eventHandlerRunnerWrapped;

    BaseEventHandlerRunnerDecorator(EventHandlerRunner eventHandlerRunner) {
        this.eventHandlerRunnerWrapped = eventHandlerRunner;
    }

    @Override
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
        // basic behavior
        eventHandlerRunnerWrapped.runHandlers(event, smartHome);
    }
}
