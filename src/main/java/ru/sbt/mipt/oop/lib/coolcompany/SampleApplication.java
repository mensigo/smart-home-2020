package ru.sbt.mipt.oop.lib.coolcompany;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.spring.BaseConfig;

public class SampleApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BaseConfig.class);
        SensorEventsManagerAdapter sensorEventsManagerAdapter = context.getBean(SensorEventsManagerAdapter.class);
        sensorEventsManagerAdapter.start();
    }
}

