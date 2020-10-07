package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.eventhandlers.EventScenarioChooser;
import ru.sbt.mipt.oop.eventhandlers.EventScenarioChooserImpl;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.io.SmartHomeDataInput;
import ru.sbt.mipt.oop.io.SmartHomeDataInputJSON;
import ru.sbt.mipt.oop.io.SmartHomeDataOutput;
import ru.sbt.mipt.oop.io.SmartHomeDataOutputJSON;
import ru.sbt.mipt.oop.objects.SmartHomeActionable;

public class Application {
    private final SmartHomeDataInput smartHomeDataInput;
    private final SmartHomeDataOutput smartHomeDataOutput;
    private final EventGenerator eventGenerator;
    private final CommandSender commandSender;
    private final EventScenarioChooser eventScenarioChooser;
    private final SmartHomeActionable smartHome;

    public Application(SmartHomeDataInput smartHomeDataInput,
                       SmartHomeDataOutput smartHomeDataOutput,
                       CommandSender commandSender,
                       EventScenarioChooser eventScenarioChooser,
                       EventGenerator eventGenerator) {
        this.smartHomeDataInput = smartHomeDataInput;
        this.smartHomeDataOutput = smartHomeDataOutput;
        this.smartHome = this.smartHomeDataInput.readSmartHomeData();
        this.commandSender = commandSender;
        this.eventScenarioChooser = eventScenarioChooser;
        this.eventGenerator = eventGenerator;;
    }

    public SmartHomeActionable getSmartHome() { return smartHome; }

    public EventGenerator getEventGenerator() { return eventGenerator; }

    public CommandSender getCommandSender() { return commandSender; }

    public EventScenarioChooser getEventScenarioChooser() { return eventScenarioChooser; }

    public static void main(String[] args) {
        Application application = new Application(
                new SmartHomeDataInputJSON("smart-home-1.js"),
                new SmartHomeDataOutputJSON("output.js"),
                new CommandSenderImpl(),
                new EventScenarioChooserImpl(),
                new RandomEventGenerator());
        RunningCycleApplication runCycleApplication = new RunningCycleApplication(
                application.getEventGenerator(),
                application.getEventScenarioChooser(),
                application.getCommandSender(),
                application.getSmartHome());
        runCycleApplication.run();
    }
}