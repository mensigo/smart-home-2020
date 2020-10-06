package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.EventGenerator;
import ru.sbt.mipt.oop.eventhandlers.EventScenarioChooser;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public class RunningCycleApplication {
    private final EventGenerator eventGenerator;
    private final EventScenarioChooser eventScenarioChooser;
    private final CommandSender commandSender;
    private final SmartHome smartHome;

    public RunningCycleApplication(EventGenerator eventGenerator,
                                   EventScenarioChooser eventScenarioChooser,
                                   CommandSender commandSender,
                                   SmartHome smartHome) {
        this.eventGenerator = eventGenerator;
        this.eventScenarioChooser = eventScenarioChooser;
        this.commandSender = commandSender;
        this.smartHome = smartHome;
    }

    public void run() {
        SensorEvent event = eventGenerator.provideNextEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventScenarioChooser.chooseAndImplementScenario(
                    event,
                    smartHome,
                    commandSender);
            event = eventGenerator.provideNextEvent();
        }
    }
}
