package ru.sbt.mipt.oop.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.Signalisation;

@Configuration
public class SignalisationConfig {
    @Bean
    Signalisation signalisation(SmartHome smartHome) {
        return smartHome.getSignalisation();
    }
}
