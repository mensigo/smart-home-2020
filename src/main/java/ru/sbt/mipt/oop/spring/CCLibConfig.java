package ru.sbt.mipt.oop.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.lib.coolcompany.CCEventTypeTranslator;
import ru.sbt.mipt.oop.lib.coolcompany.CCEventTypeTranslatorImpl;
import ru.sbt.mipt.oop.lib.coolcompany.EventHandlerRunnerAdapter;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Map;

import static java.util.Map.entry;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

@Configuration
public class CCLibConfig {
    @Bean
    EventHandlerRunnerAdapter eventHandlerRunnerAdapter(CCEventTypeTranslator ccEventTypeTranslator,
                                                        SignalisationEventHandlerRunnerDecorator decoratedEventHandlerRunner,
                                                        SmartHome smartHome) {
        return new EventHandlerRunnerAdapter(ccEventTypeTranslator, decoratedEventHandlerRunner, smartHome);
    }

    @Bean
    CCEventTypeTranslator ccEventTypeTranslator(Map<String, SensorEventType> ccEventTypeToSensorEventTypeMap) {
        return new CCEventTypeTranslatorImpl(ccEventTypeToSensorEventTypeMap);
    }

    @Bean
    Map<String, SensorEventType> ccEventTypeToSensorEventTypeMap() {
        return Map.ofEntries(
                entry("LightIsOn", LIGHT_ON),
                entry("LightIsOff", LIGHT_OFF),
                entry("DoorIsOpen", DOOR_OPEN),
                entry("DoorIsClosed", DOOR_CLOSE),
                entry("DoorIsLocked", UNKNOWN_EVENT),
                entry("DoorIsUnlocked", UNKNOWN_EVENT)
                // more CCSensorEvent types can be added here
        );
    }
}
