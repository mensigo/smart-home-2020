package ru.sbt.mipt.oop.spring;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.lib.coolcompany.CCEventTypeTranslator;
import ru.sbt.mipt.oop.lib.coolcompany.SensorEventsManagerAdapter;
import ru.sbt.mipt.oop.lib.coolcompany.SensorEventsManagerAdapterImpl;
import ru.sbt.mipt.oop.objects.SmartHome;

@Configuration
@ComponentScan
@Import({EventHandlersConfig.class, IOConfig.class, DecoratorConfig.class, CCLibConfig.class})
public class BaseConfig {
    @Bean
    SensorEventsManagerAdapter sensorEventsManagerAdapter(
            SensorEventsManager sensorEventsManager,
            CCEventTypeTranslator ccEventTypeTranslator,
            SmartHome smartHome,
            SignalisationEventHandlerRunnerDecorator signalisationEventHandlerRunnerDecorator) {
        SensorEventsManagerAdapter sensorEventsManagerAdapter = new SensorEventsManagerAdapterImpl(
                sensorEventsManager,
                ccEventTypeTranslator,
                smartHome
        );
        sensorEventsManagerAdapter.registerEventHandler(signalisationEventHandlerRunnerDecorator::runHandlers);
        return sensorEventsManagerAdapter;
    }
}
