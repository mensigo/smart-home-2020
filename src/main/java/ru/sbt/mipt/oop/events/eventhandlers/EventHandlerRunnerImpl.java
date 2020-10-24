package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.List;

public class EventHandlerRunnerImpl implements EventHandlerRunner {
    private final CommandSender commandSender;
    private List<EventHandler> eventHandlerList;

    public EventHandlerRunnerImpl(CommandSender commandSender) {
        this.commandSender = commandSender;
        initializeEventHandlerList();
    }

    @Override
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
        for (EventHandler eventHandler : eventHandlerList) {
            eventHandler.handleEvent(event, smartHome);
        }
    }

    private void initializeEventHandlerList() {
        eventHandlerList = Arrays.asList(
                new AlarmActivateEventHandler(),
                new AlarmDeactivateEventHandler(),
                new LightOnEventHandler(commandSender),
                new LightOffEventHandler(commandSender),
                new DoorOpenEventHandler(commandSender),
                new DoorCloseEventHandler(commandSender),
                new DoorCloseInHallEventHandler(commandSender)
                // more eventHandlers can be added here
        );
    }
}
