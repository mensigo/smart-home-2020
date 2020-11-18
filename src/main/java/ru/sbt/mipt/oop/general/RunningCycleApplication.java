package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.eventgenerators.EventGenerator;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.objects.SmartHome;

public class RunningCycleApplication {
    private final EventGenerator eventGenerator;
    private final EventHandlerRunner eventScenarioChooser;
    private final SmartHome smartHome;

    public RunningCycleApplication(EventGenerator eventGenerator,
                                   EventHandlerRunner eventScenarioChooser,
                                   SmartHome smartHome) {
        this.eventGenerator = eventGenerator;
        this.eventScenarioChooser = eventScenarioChooser;
        this.smartHome = smartHome;
    }

    public void run() {
        SensorEvent event = eventGenerator.provideNextEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventScenarioChooser.runHandlers(event, smartHome);
            event = eventGenerator.provideNextEvent();
        }
    }
}
