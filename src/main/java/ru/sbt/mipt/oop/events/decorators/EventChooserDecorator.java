package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
<<<<<<< HEAD
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerChooser;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventChooserDecorator implements EventHandlerChooser {
    EventHandlerChooser eventScenarioChooserToWrap;

    EventChooserDecorator(EventHandlerChooser eventScenarioChooser) {
=======
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventChooserDecorator implements EventHandlerRunner {
    EventHandlerRunner eventScenarioChooserToWrap;

    EventChooserDecorator(EventHandlerRunner eventScenarioChooser) {
>>>>>>> hometask-2
        this.eventScenarioChooserToWrap = eventScenarioChooser;
    }

    @Override
<<<<<<< HEAD
    public void chooseAndRunHandler(SensorEvent event, SmartHome smartHome) {
        eventScenarioChooserToWrap.chooseAndRunHandler(event, smartHome);
=======
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
        eventScenarioChooserToWrap.runHandlers(event, smartHome);
>>>>>>> hometask-2
    }
}
