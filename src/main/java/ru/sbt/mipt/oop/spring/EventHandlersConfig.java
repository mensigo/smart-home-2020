package ru.sbt.mipt.oop.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.eventhandlers.*;

import java.util.Arrays;
import java.util.List;

@Configuration
public class EventHandlersConfig {
    final String hallName = "hall"; // to be scanned

    @Bean
    AlarmActivateEventHandler alarmActivateEventHandler() {
        return new AlarmActivateEventHandler();
    }

    @Bean
    AlarmDeactivateEventHandler alarmDeactivateEventHandler() {
        return new AlarmDeactivateEventHandler();
    }

    @Bean
    LightOnEventHandler lightOnEventHandler(CommandSender commandSender) {
        return new LightOnEventHandler(commandSender);
    }

    @Bean
    LightOffEventHandler lightOffEventHandler(CommandSender commandSender) {
        return new LightOffEventHandler(commandSender);
    }

    @Bean
    DoorOpenEventHandler doorOpenEventHandler(CommandSender commandSender) {
        return new DoorOpenEventHandler(commandSender);
    }

    @Bean
    DoorCloseEventHandler doorCloseEventHandler(CommandSender commandSender) {
        return new DoorCloseEventHandler(commandSender);
    }

    @Bean
    DoorCloseInHallEventHandler doorCloseInHallEventHandler(String hallName, CommandSender commandSender) {
        return new DoorCloseInHallEventHandler(hallName, commandSender);
    }

    @Bean
    UnknownEventHandler unknownEventHandler() {
        return new UnknownEventHandler();
    }

    @Bean
    EventHandlerRunner eventHandlerRunner(CommandSender commandSender) {
        return new EventHandlerRunnerImpl(eventHandlerList(hallName, commandSender));
    }

    @Bean
    CommandSender commandSender() {
        return new CommandSenderImpl();
    }

    @Bean
    List<EventHandler> eventHandlerList(String hallName, CommandSender commandSender) {
        return Arrays.asList(
                alarmActivateEventHandler(),
                alarmDeactivateEventHandler(),
                lightOnEventHandler(commandSender),
                lightOffEventHandler(commandSender),
                doorOpenEventHandler(commandSender),
                doorCloseEventHandler(commandSender),
                doorCloseInHallEventHandler(hallName, commandSender),
                unknownEventHandler()
                // more eventHandlers can be added here
        );
    }
}
