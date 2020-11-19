package ru.sbt.mipt.oop.general;

import com.anothercompany.rc.RemoteControl;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.events.decorators.SimpleSMSSender;
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


public class Application {
    private final SmartHome smartHome; // is hidden from user

    public Application(SmartHomeDataInput smartHomeDataInput) {
        this.smartHome = smartHomeDataInput.readSmartHomeData();
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
        EventHandlerRunner eventHandlerRunner = new SignalisationEventHandlerRunnerDecorator(
                new EventHandlerRunnerImpl(eventHandlerList),
                new SimpleSMSSender()
        );
        EventGenerator eventGenerator = new RandomEventGenerator();
        Application application = new Application(new CustomSmartHomeDataInput());
        RunningCycleApplication runCycleApplication = new RunningCycleApplication(
                eventGenerator,
                eventHandlerRunner,
                application.smartHome);
        runCycleApplication.run();
    }
}
