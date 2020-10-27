package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.events.decorators.SignalisationSMSSender;
import ru.sbt.mipt.oop.events.eventgenerators.EventGenerator;
import ru.sbt.mipt.oop.events.eventgenerators.RandomEventGenerator;
import ru.sbt.mipt.oop.events.eventhandlers.*;
import ru.sbt.mipt.oop.io.*;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.List;

public class Application {
    private final SmartHomeDataInput smartHomeDataInput;
    private final SmartHomeDataOutput smartHomeDataOutput;
    private final EventGenerator eventGenerator;
    private final EventHandlerRunner eventScenarioChooser;
    private final SmartHome smartHome;

    public Application(SmartHomeDataInput smartHomeDataInput,
                       SmartHomeDataOutput smartHomeDataOutput,
                       EventHandlerRunner eventScenarioChooser,
                       EventGenerator eventGenerator) {
        this.smartHomeDataInput = smartHomeDataInput;
        this.smartHomeDataOutput = smartHomeDataOutput;
        this.smartHome = this.smartHomeDataInput.readSmartHomeData();
        this.eventScenarioChooser = eventScenarioChooser;
        this.eventGenerator = eventGenerator;
    }

    public static void main(String[] args) {
        String hallName = "hall";
        CommandSender commandSender = new CommandSenderImpl();
        List<EventHandler> eventHandlerList = Arrays.asList(
                new AlarmActivateEventHandler(),
                new AlarmDeactivateEventHandler(),
                new LightOnEventHandler(commandSender),
                new LightOffEventHandler(commandSender),
                new DoorOpenEventHandler(commandSender),
                new DoorCloseEventHandler(commandSender),
                new DoorCloseInHallEventHandler(hallName, commandSender)
                // more eventHandlers can be added here
        );
        Application application = new Application(
                new CustomSmartHomeDataInput(),
                new CustomSmartHomeDataOutput(),
                new SignalisationEventHandlerRunnerDecorator(
                        new EventHandlerRunnerImpl(eventHandlerList),
                        new SignalisationSMSSender()
                ),
                new RandomEventGenerator()
        );
        RunningCycleApplication runCycleApplication = new RunningCycleApplication(
                application.eventGenerator,
                application.eventScenarioChooser,
                application.smartHome);
        runCycleApplication.run();
    }
}
