package ru.sbt.mipt.oop.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.events.decorators.SMSSender;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.events.decorators.SimpleSMSSender;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;

@Configuration
public class DecoratorConfig {
    @Bean
    SignalisationEventHandlerRunnerDecorator signalisationEventHandlerRunnerDecorator(
            EventHandlerRunner eventHandlerRunner,
            SMSSender smsSender) {
        return new SignalisationEventHandlerRunnerDecorator(eventHandlerRunner, smsSender);
    }

    @Bean
    SMSSender smsSender() {
        return new SimpleSMSSender();
    }
}
