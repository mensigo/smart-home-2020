package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.eventgenerators.EventGenerator;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerChooser;
import ru.sbt.mipt.oop.objects.SmartHome;

public class RunningCycleApplication {
    private final EventGenerator eventGenerator;
    private final EventHandlerChooser eventScenarioChooser;
    private final SmartHome smartHome;

    public RunningCycleApplication(EventGenerator eventGenerator,
                                   EventHandlerChooser eventScenarioChooser,
                                   SmartHome smartHome) {
        this.eventGenerator = eventGenerator;
        this.eventScenarioChooser = eventScenarioChooser;
        this.smartHome = smartHome;
    }

    public void run() {
        SensorEvent event = eventGenerator.provideNextEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventScenarioChooser.chooseAndRunHandler(event, smartHome);
            event = eventGenerator.provideNextEvent();
        }
    }
}
