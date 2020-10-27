package ru.sbt.mipt.oop.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.eventhandlers.*;

import java.util.Arrays;
import java.util.List;

@Configuration
public class EventHandlersConfig {

    private final String hallName = "hall";

    @Bean
    AlarmActivateEventHandler createAlarmActivateEventHandler() {
        return new AlarmActivateEventHandler();
    }

    @Bean
    AlarmDeactivateEventHandler createAlarmDeactivateEventHandler() {
        return new AlarmDeactivateEventHandler();
    }

    @Bean
    LightOnEventHandler createLightOnEventHandler(CommandSender commandSender) {
        return new LightOnEventHandler(commandSender);
    }

    @Bean
    LightOffEventHandler createLightOffEventHandler(CommandSender commandSender) {
        return new LightOffEventHandler(commandSender);
    }

    @Bean
    DoorOpenEventHandler createDoorOpenEventHandler(CommandSender commandSender) {
        return new DoorOpenEventHandler(commandSender);
    }

    @Bean
    DoorCloseEventHandler createDoorCloseEventHandler(CommandSender commandSender) {
        return new DoorCloseEventHandler(commandSender);
    }

    @Bean
    DoorCloseInHallEventHandler createDoorCloseInHallEventHandler(String hallName, CommandSender commandSender) {
        return new DoorCloseInHallEventHandler(hallName, commandSender);
    }

    @Bean
    UnknownEventHandler createUnknownEventHandler() {
        return new UnknownEventHandler();
    }

    @Bean
    EventHandlerRunner createEventHandlerRunner(CommandSender commandSender) {
        return new EventHandlerRunnerImpl(createEventHandlerList(hallName, commandSender));
    }

    @Bean
    CommandSender createCommandSender() {
        return new CommandSenderImpl();
    }

    @Bean
    List<EventHandler> createEventHandlerList(String hallName, CommandSender commandSender) {
        return Arrays.asList(
                createAlarmActivateEventHandler(),
                createAlarmDeactivateEventHandler(),
                createLightOnEventHandler(commandSender),
                createLightOffEventHandler(commandSender),
                createDoorOpenEventHandler(commandSender),
                createDoorCloseEventHandler(commandSender),
                createDoorCloseInHallEventHandler(hallName, commandSender),
                createUnknownEventHandler()
                // more eventHandlers can be added here
        );
    }
}
