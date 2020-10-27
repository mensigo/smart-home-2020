package ru.sbt.mipt.oop.lib.coolcompany;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.events.decorators.SignalisationSMSSender;
import ru.sbt.mipt.oop.events.eventhandlers.*;
import ru.sbt.mipt.oop.io.CustomSmartHomeDataInput;
import ru.sbt.mipt.oop.io.CustomSmartHomeDataOutput;
import ru.sbt.mipt.oop.io.SmartHomeDataInput;
import ru.sbt.mipt.oop.io.SmartHomeDataOutput;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.List;

public class SampleApplication {
    private final SmartHomeDataInput smartHomeDataInput;
    private final SmartHomeDataOutput smartHomeDataOutput;
    private final EventHandlerRunner eventScenarioRunner;
    private final SmartHome smartHome;
    private final SensorEventsManager sensorEventsManager;
    private final CCEventTypeTranslator ccEventTypeTranslator;

    public SampleApplication(SmartHomeDataInput smartHomeDataInput,
                             SmartHomeDataOutput smartHomeDataOutput,
                             EventHandlerRunner eventScenarioRunner,
                             SensorEventsManager sensorEventsManager,
                             CCEventTypeTranslator ccEventTypeTranslator) {
        this.smartHomeDataInput = smartHomeDataInput;
        this.smartHomeDataOutput = smartHomeDataOutput;
        this.smartHome = this.smartHomeDataInput.readSmartHomeData();
        this.eventScenarioRunner = eventScenarioRunner;
        this.sensorEventsManager = sensorEventsManager;
        this.ccEventTypeTranslator = ccEventTypeTranslator;
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
                new DoorCloseInHallEventHandler(hallName, commandSender),
                new UnknownEventHandler()
                // more eventHandlers can be added here
        );
        SampleApplication application = new SampleApplication(
                new CustomSmartHomeDataInput(),
                new CustomSmartHomeDataOutput(),
                new SignalisationEventHandlerRunnerDecorator(
                        new EventHandlerRunnerImpl(eventHandlerList),
                        new SignalisationSMSSender()
                ),
                new SensorEventsManager(),
                new CCEventTypeTranslatorImpl()
        );
        SensorEventsManagerAdapter sensorEventsManagerAdapter = new SensorEventsManagerAdapterImpl(
                application.sensorEventsManager,
                application.ccEventTypeTranslator,
                application.smartHome
        );
        sensorEventsManagerAdapter.registerEventHandler(application.eventScenarioRunner::runHandlers);
        sensorEventsManagerAdapter.start();
    }
}

