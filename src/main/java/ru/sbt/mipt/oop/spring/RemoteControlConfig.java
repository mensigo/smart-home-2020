package ru.sbt.mipt.oop.spring;

import com.anothercompany.rc.RemoteControl;
import com.anothercompany.rc.RemoteControlRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.lib.remotecontrol.RemoteControlImpl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.*;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.Signalisation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RemoteControlConfig {

    @Value("#{hallName}")
    String hallName;
    @Value("#{standardSigActivateCode}")
    String standardSigActivateCode;

    @Bean
    RemoteControlRegistry createRemoteControlRegistry(CommandSender commandSender, SmartHome smartHome) {
        Map<String, RemoteControl> rcMap = createRemoteControlWithIdMap(commandSender, smartHome);
        RemoteControlRegistry registry = new RemoteControlRegistry();
        for (String rcId : rcMap.keySet()) {
            registry.registerRemoteControl(rcMap.get(rcId), rcId);
        }
        return registry;
    }

    @Bean
    RemoteControl createRemoteControl(String id, List<ButtonCommand> buttonsList) {
        return new RemoteControlImpl(id, buttonsList);
    }

    @Bean
    DoorCloseInHallButtonCommand createDoorCloseInHallButtonCommand(String buttonCode,
                                                                    SmartHome smartHome,
                                                                    CommandSender commandSender,
                                                                    boolean isQuiet,
                                                                    String hallName) {

        return new DoorCloseInHallButtonCommand(buttonCode, smartHome, commandSender, isQuiet, hallName);
    }

    @Bean
    LightOffAllButtonCommand createLightOffAllButtonCommand(String buttonCode,
                             SmartHome smartHome,
                             CommandSender commandSender,
                             boolean isQuiet) {
        return new LightOffAllButtonCommand(buttonCode, smartHome, commandSender, isQuiet);
    }

    @Bean
    LightOnAllButtonCommand createLightOnAllButtonCommand(String buttonCode,
                                                          SmartHome smartHome,
                                                          CommandSender commandSender,
                                                          boolean isQuiet) {
        return new LightOnAllButtonCommand(buttonCode, smartHome, commandSender, isQuiet);
    }

    @Bean
    LightOnInHallButtonCommand createLightOnInHallButtonCommand(String buttonCode,
                                                                SmartHome smartHome,
                                                                CommandSender commandSender,
                                                                boolean isQuiet,
                                                                String hallName) {
        return new LightOnInHallButtonCommand(buttonCode, smartHome, commandSender, isQuiet, hallName);
    }

    @Bean
    SignalisationActivateButtonCommand createSignalisationActivateButtonCommand(String buttonCode,
                                                                                String enteredSignalCode,
                                                                                Signalisation signalisation) {
        return new SignalisationActivateButtonCommand(buttonCode, enteredSignalCode, signalisation);
    }

    @Bean
    SignalisationAlarmButtonCommand createSignalisationAlarmButtonCommand(String buttonCode,
                                                                          Signalisation signalisation) {
        return new SignalisationAlarmButtonCommand(buttonCode, signalisation);
    }

    @Bean
    List<ButtonCommand> createSampleButtonsList(CommandSender commandSender, SmartHome smartHome) {
        Signalisation signalisation = smartHome.getSignalisation();
        return Arrays.asList(
                createLightOffAllButtonCommand("A", smartHome, commandSender, false),
                createLightOnAllButtonCommand("B", smartHome, commandSender, false),
                createDoorCloseInHallButtonCommand("C", smartHome, commandSender, false, hallName),
                createLightOnInHallButtonCommand("D", smartHome, commandSender, false, hallName),
                createSignalisationActivateButtonCommand("1", standardSigActivateCode, signalisation),
                createSignalisationAlarmButtonCommand("2", signalisation)
                // more button commands can be added here
        );
    }

    @Bean
    Map<String, RemoteControl> createRemoteControlWithIdMap(CommandSender commandSender, SmartHome smartHome) {
        List<ButtonCommand> sampleButtonsList = createSampleButtonsList(commandSender, smartHome);
        Map<String, RemoteControl> map = new HashMap<>();
        String rcId = "abc123";
        map.put(rcId, createRemoteControl(rcId, sampleButtonsList));
        // more remote controls can be added here
        return map;
    }
}
