package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerChooser;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventChooserDecorator implements EventHandlerChooser {
    EventHandlerChooser eventScenarioChooserToWrap;

    EventChooserDecorator(EventHandlerChooser eventScenarioChooser) {
        this.eventScenarioChooserToWrap = eventScenarioChooser;
    }

    @Override
    public void chooseAndRunHandler(SensorEvent event, SmartHome smartHome) {
        eventScenarioChooserToWrap.chooseAndRunHandler(event, smartHome);
    }
}
