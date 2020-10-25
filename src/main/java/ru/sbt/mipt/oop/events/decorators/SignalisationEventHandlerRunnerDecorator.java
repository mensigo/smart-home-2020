package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.Signalisation;

public class SignalisationEventHandlerRunnerDecorator extends BaseEventHandlerRunnerDecorator {
    private final SMSSender smsSender;

    public SignalisationEventHandlerRunnerDecorator(EventHandlerRunner eventHandlerRunner) {
        super(eventHandlerRunner);
        smsSender = new SignalisationSMSSender();
    }

    @Override
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
        if (event instanceof SimpleSensorEvent) {
            // simple sensor event
            Signalisation signalisation = smartHome.getSignalisation();
            String signalisationStateName = signalisation.getState().getName();
            if (signalisationStateName.equals("Alarmed")) {
                smsSender.sendSMS(event.toString());
            } else if (signalisationStateName.equals("Activated")) {
                signalisation.alarm();
                smsSender.sendSMS(event.toString());
            } else {
                super.runHandlers(event, smartHome);
            }
        } else {
            // signalisation event
            super.runHandlers(event, smartHome);
        }
    }
}
