package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

public class SignalisationEventHandlerRunnerDecorator implements EventHandlerRunner {
    private final EventHandlerRunner eventHandlerRunnerWrapped;
    private final SMSSender smsSender;

    public SignalisationEventHandlerRunnerDecorator(EventHandlerRunner eventHandlerRunner,
                                                    SMSSender smsSender) {
        this.eventHandlerRunnerWrapped = eventHandlerRunner;
        this.smsSender = smsSender;
    }

    @Override
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
        if (event instanceof SimpleSensorEvent) {
            // simple sensor event
            SignalisationImpl signalisation = (SignalisationImpl) smartHome.getSignalisation();
            if (signalisation.isAlarmed()) {
                smsSender.sendSMS(event.toString());
            } else if (signalisation.isActivated()) {
                signalisation.alarm();
                smsSender.sendSMS(event.toString());
            } else {
                eventHandlerRunnerWrapped.runHandlers(event, smartHome);
            }
        } else {
            // signalisation event
            eventHandlerRunnerWrapped.runHandlers(event, smartHome);
        }
    }
}
