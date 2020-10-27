package ru.sbt.mipt.oop.spring;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.io.SmartHomeDataInput;
import ru.sbt.mipt.oop.lib.coolcompany.CCEventTypeTranslator;
import ru.sbt.mipt.oop.lib.coolcompany.CCEventTypeTranslatorImpl;
import ru.sbt.mipt.oop.lib.coolcompany.SensorEventsManagerAdapter;
import ru.sbt.mipt.oop.lib.coolcompany.SensorEventsManagerAdapterImpl;
import ru.sbt.mipt.oop.objects.SmartHome;

@Configuration
@ComponentScan
@Import({EventHandlersConfig.class, IOConfig.class, DecoratorConfig.class})
public class BaseConfig {

    @Autowired SignalisationEventHandlerRunnerDecorator createSignalisationEventHandlerRunnerDecorator;
    @Autowired SmartHomeDataInput createSmartHomeDataInput;

    @Bean
    SensorEventsManager createSensorEventsManager() {
        return new SensorEventsManager();
    }

    @Bean
    CCEventTypeTranslator createCCEventTypeTranslator() {
        return new CCEventTypeTranslatorImpl();
    }

    @Bean
    SmartHome createSmartHome() {
        return createSmartHomeDataInput.readSmartHomeData();
    }

    @Bean
    SensorEventsManagerAdapter createSensorEventsManagerAdapter(SensorEventsManager sensorEventsManager,
                                                                CCEventTypeTranslator ccEventTypeTranslator,
                                                                SmartHome smartHome) {
        SensorEventsManagerAdapter sensorEventsManagerAdapter = new SensorEventsManagerAdapterImpl(
                sensorEventsManager,
                ccEventTypeTranslator,
                smartHome
        );
        EventHandlerRunner eventHandlerRunner = createSignalisationEventHandlerRunnerDecorator;
        sensorEventsManagerAdapter.registerEventHandler(eventHandlerRunner::runHandlers);
        return sensorEventsManagerAdapter;
    }
}
