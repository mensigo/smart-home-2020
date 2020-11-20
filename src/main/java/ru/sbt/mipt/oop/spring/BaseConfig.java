package ru.sbt.mipt.oop.spring;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.lib.coolcompany.EventHandlerRunnerAdapter;

@Configuration
@ComponentScan
@Import({EventHandlersConfig.class, IOConfig.class, DecoratorConfig.class, CCLibConfig.class})
public class BaseConfig {
    @Bean
    SensorEventsManager sensorEventsManager(EventHandlerRunnerAdapter adaptedEventHandlerRunner) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(adaptedEventHandlerRunner);
        return sensorEventsManager;
    }
}
