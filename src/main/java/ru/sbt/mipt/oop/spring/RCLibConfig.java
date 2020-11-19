package ru.sbt.mipt.oop.spring;

import com.anothercompany.rc.RemoteControl;
import com.anothercompany.rc.RemoteControlRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.lib.remotecontrol.RemoteControlImpl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.*;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.Signalisation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@Configuration
public class RCLibConfig {
    final String hallName = "hall";
    final String standardSigActivateCode = "0000";
    final String standardId = "abc123";

    @Bean
    RemoteControlRegistry remoteControlRegistry(Map<String, RemoteControl> rcMap) {
        RemoteControlRegistry registry = new RemoteControlRegistry();
        for (String rcId : rcMap.keySet()) {
            registry.registerRemoteControl(rcMap.get(rcId), rcId);
        }
        return registry;
    }

    // is not a bean because it doesn't exist out of RemoteControl
    DoorCloseInHallButtonCommand doorCloseInHallButtonCommand(
            String buttonCode,
            SmartHome smartHome,
            CommandSender commandSender,
            boolean isQuiet,
            String hallName) {
        return new DoorCloseInHallButtonCommand(buttonCode, smartHome, commandSender, isQuiet, hallName);
    }

    LightOffAllButtonCommand lightOffAllButtonCommand(
            String buttonCode,
            SmartHome smartHome,
            CommandSender commandSender,
            boolean isQuiet) {
        return new LightOffAllButtonCommand(buttonCode, smartHome, commandSender, isQuiet);
    }

    LightOnAllButtonCommand lightOnAllButtonCommand(
            String buttonCode,
            SmartHome smartHome,
            CommandSender commandSender,
            boolean isQuiet) {
        return new LightOnAllButtonCommand(buttonCode, smartHome, commandSender, isQuiet);
    }

    LightOnInHallButtonCommand lightOnInHallButtonCommand(
            String buttonCode,
            SmartHome smartHome,
            CommandSender commandSender,
            boolean isQuiet,
            String hallName) {
        return new LightOnInHallButtonCommand(buttonCode, smartHome, commandSender, isQuiet, hallName);
    }

    SignalisationActivateButtonCommand signalisationActivateButtonCommand(
            String buttonCode,
            String enteredSignalCode,
            Signalisation signalisation) {
        return new SignalisationActivateButtonCommand(buttonCode, enteredSignalCode, signalisation);
    }

    SignalisationAlarmButtonCommand signalisationAlarmButtonCommand(String buttonCode, Signalisation signalisation) {
        return new SignalisationAlarmButtonCommand(buttonCode, signalisation);
    }

    @Bean
    List<ButtonCommand> sampleButtonsList(CommandSender commandSender, SmartHome smartHome) {
        Signalisation signalisation = smartHome.getSignalisation();
        return Arrays.asList(
                lightOffAllButtonCommand("A", smartHome, commandSender, false),
                lightOnAllButtonCommand("B", smartHome, commandSender, false),
                doorCloseInHallButtonCommand("C", smartHome, commandSender, false, hallName),
                lightOnInHallButtonCommand("D", smartHome, commandSender, false, hallName),
                signalisationActivateButtonCommand("1", standardSigActivateCode, signalisation),
                signalisationAlarmButtonCommand("2", signalisation)
                // more button commands can be added here
        );
    }

    @Bean
    List<String> allowedButtonCodes() {
        return Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4");
        // more allowed button codes can be added here
    }

    @Bean
    Map<String, RemoteControl> idToRemoteControlMap(List<ButtonCommand> buttonsList, List<String> allowedButtonsList) {
        return Map.ofEntries(
            entry(standardId, new RemoteControlImpl(standardId, buttonsList, allowedButtonsList))
            // more remote controls can be added here
        );
    }
}
