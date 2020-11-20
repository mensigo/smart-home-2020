package ru.sbt.mipt.oop.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.io.CustomSmartHomeDataInput;
import ru.sbt.mipt.oop.io.SmartHomeDataInput;
import ru.sbt.mipt.oop.objects.SmartHome;

@Configuration
public class IOConfig {
    @Bean
    SmartHome smartHome(SmartHomeDataInput smartHomeDataInput) {
        return smartHomeDataInput.readSmartHomeData();
    }

    @Bean
    SmartHomeDataInput smartHomeDataInput() {
        return new CustomSmartHomeDataInput();
    }
}
