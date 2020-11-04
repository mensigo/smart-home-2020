package ru.sbt.mipt.oop.general;

import com.anothercompany.rc.RemoteControl;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.events.decorators.SignalisationSMSSender;
import ru.sbt.mipt.oop.events.eventgenerators.EventGenerator;
import ru.sbt.mipt.oop.events.eventgenerators.RandomEventGenerator;
import ru.sbt.mipt.oop.events.eventhandlers.*;
import ru.sbt.mipt.oop.io.CustomSmartHomeDataInput;
import ru.sbt.mipt.oop.io.CustomSmartHomeDataOutput;
import ru.sbt.mipt.oop.io.SmartHomeDataInput;
import ru.sbt.mipt.oop.io.SmartHomeDataOutput;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.List;

public class LegacyApplication {
    private final SmartHomeDataInput smartHomeDataInput;
    private final SmartHomeDataOutput smartHomeDataOutput;
    private final EventGenerator eventGenerator;
    private final EventHandlerRunner eventScenarioChooser;
    private final RemoteControl remoteControl;
    private final SmartHome smartHome;

    public LegacyApplication(SmartHomeDataInput smartHomeDataInput,
                             SmartHomeDataOutput smartHomeDataOutput,
                             EventHandlerRunner eventScenarioChooser,
                             EventGenerator eventGenerator,
                             RemoteControl remoteControl
                             ) {
        this.smartHomeDataInput = smartHomeDataInput;
        this.smartHomeDataOutput = smartHomeDataOutput;
        this.smartHome = this.smartHomeDataInput.readSmartHomeData();
        this.eventScenarioChooser = eventScenarioChooser;
        this.eventGenerator = eventGenerator;
        this.remoteControl = remoteControl;
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
        LegacyApplication application = new LegacyApplication(
                new CustomSmartHomeDataInput(),
                new CustomSmartHomeDataOutput(),
                new SignalisationEventHandlerRunnerDecorator(
                        new EventHandlerRunnerImpl(eventHandlerList),
                        new SignalisationSMSSender()
                ),
                new RandomEventGenerator(),
                null // need smartHome to be initialized -> use springApp
        );
        RunningCycleApplication runCycleApplication = new RunningCycleApplication(
                application.eventGenerator,
                application.eventScenarioChooser,
                application.smartHome);
        runCycleApplication.run();
    }
}
