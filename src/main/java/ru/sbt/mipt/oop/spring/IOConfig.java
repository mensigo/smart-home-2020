package ru.sbt.mipt.oop.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.io.CustomSmartHomeDataInput;
import ru.sbt.mipt.oop.io.CustomSmartHomeDataOutput;
import ru.sbt.mipt.oop.io.SmartHomeDataInput;
import ru.sbt.mipt.oop.io.SmartHomeDataOutput;

@Configuration
public class IOConfig {

    @Bean
    SmartHomeDataInput createSmartHomeDataInput() {
        return new CustomSmartHomeDataInput();
    }

    @Bean
    SmartHomeDataOutput createSmartHomeDataOutput() {
        return new CustomSmartHomeDataOutput();
    }
}
