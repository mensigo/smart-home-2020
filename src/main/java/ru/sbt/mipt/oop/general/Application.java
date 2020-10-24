package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventChooserDecorator;
import ru.sbt.mipt.oop.events.eventgenerators.EventGenerator;
import ru.sbt.mipt.oop.events.eventgenerators.RandomEventGenerator;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunnerImpl;
import ru.sbt.mipt.oop.io.SmartHomeDataInput;
import ru.sbt.mipt.oop.io.JSONSmartHomeDataInput;
import ru.sbt.mipt.oop.io.SmartHomeDataOutput;
import ru.sbt.mipt.oop.io.JSONSmartHomeDataOutput;
import ru.sbt.mipt.oop.objects.SmartHome;

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
        Application application = new Application(
                new JSONSmartHomeDataInput("input.js"),
                new JSONSmartHomeDataOutput("output.js"),
                new SignalisationEventChooserDecorator(new EventHandlerRunnerImpl(new CommandSenderImpl())),
//                new EventHandlerRunnerImpl(new CommandSenderImpl()),
                new RandomEventGenerator());
        RunningCycleApplication runCycleApplication = new RunningCycleApplication(
                application.eventGenerator,
                application.eventScenarioChooser,
                application.smartHome);
        runCycleApplication.run();
    }
}
