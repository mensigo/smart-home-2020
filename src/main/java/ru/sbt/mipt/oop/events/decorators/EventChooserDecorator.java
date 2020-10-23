package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventChooserDecorator implements EventHandlerRunner {
    EventHandlerRunner eventScenarioChooserToWrap;

    EventChooserDecorator(EventHandlerRunner eventScenarioChooser) {
        this.eventScenarioChooserToWrap = eventScenarioChooser;
    }

    @Override
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
        eventScenarioChooserToWrap.runHandlers(event, smartHome);
    }
}
