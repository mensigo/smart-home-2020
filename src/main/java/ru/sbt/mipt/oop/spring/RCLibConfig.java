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
    private final String hallName = "hall";
    private final String standardAccessCode = "0000";
    private final String standardRcId = "abc123";
    private final boolean isQuiet = false;

    // necessary bean (for signalisationActivateButtonCommand)
    @Bean
    String standardStringValue() {
        return "";
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(Map<String, RemoteControl> rcMap) {
        RemoteControlRegistry registry = new RemoteControlRegistry();
        for (String rcId : rcMap.keySet()) {
            registry.registerRemoteControl(rcMap.get(rcId), rcId);
        }
        return registry;
    }

    @Bean
    RemoteControl remoteControl(String rcId, Map<String, ButtonCommand> buttons, List<String> allowedButtonCodes) {
        return new RemoteControlImpl(rcId, buttons, allowedButtonCodes);
    }

    @Bean
    DoorCloseInHallButtonCommand doorCloseInHallButtonCommand(SmartHome smartHome,
                                                              CommandSender commandSender) {
        return new DoorCloseInHallButtonCommand(smartHome, commandSender, isQuiet, hallName);
    }

    @Bean
    LightOffAllButtonCommand lightOffAllButtonCommand(SmartHome smartHome,
                                                      CommandSender commandSender) {
        return new LightOffAllButtonCommand(smartHome, commandSender, isQuiet);
    }

    @Bean
    LightOnAllButtonCommand lightOnAllButtonCommand(SmartHome smartHome,
                                                    CommandSender commandSender) {
        return new LightOnAllButtonCommand(smartHome, commandSender, isQuiet);
    }

    @Bean
    LightOnInHallButtonCommand lightOnInHallButtonCommand(SmartHome smartHome,
                                                          CommandSender commandSender) {
        return new LightOnInHallButtonCommand(smartHome, commandSender, isQuiet, hallName);
    }

    @Bean
    SignalisationActivateButtonCommand signalisationActivateButtonCommand(String standardEnteredCode,
                                                                          Signalisation signalisation) {
        return new SignalisationActivateButtonCommand(standardEnteredCode, signalisation);
    }

    @Bean
    SignalisationAlarmButtonCommand signalisationAlarmButtonCommand(Signalisation signalisation) {
        return new SignalisationAlarmButtonCommand(signalisation);
    }

    @Bean
    Map<String, ButtonCommand> sampleButtonCodeToButtonMap(CommandSender commandSender, SmartHome smartHome) {
        Signalisation signalisation = smartHome.getSignalisation();
        return Map.ofEntries(
                entry("A", lightOffAllButtonCommand(smartHome, commandSender)),
                entry("B", lightOnAllButtonCommand(smartHome, commandSender)),
                entry("C", doorCloseInHallButtonCommand(smartHome, commandSender)),
                entry("D", lightOnInHallButtonCommand(smartHome, commandSender)),
                entry("1", signalisationActivateButtonCommand(standardAccessCode, signalisation)),
                entry("2", signalisationAlarmButtonCommand(signalisation))
                // more button commands can be added here
        );
    }

    @Bean
    List<String> allowedButtonCodesList() {
        return Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4");
        // more allowed button codes can be added here
    }

    @Bean
    Map<String, RemoteControl> SampleRemoteControlIdToRemoteControlMap(Map<String, ButtonCommand> buttons,
                                                                       List<String> allowedButtonCodes) {
        return Map.ofEntries(
            entry(standardRcId, remoteControl(standardRcId, buttons, allowedButtonCodes))
            // more remote controls can be added here
        );
    }
}
