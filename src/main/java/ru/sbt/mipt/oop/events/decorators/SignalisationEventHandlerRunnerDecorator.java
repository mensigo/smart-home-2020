package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalStateName;
import ru.sbt.mipt.oop.signalisation.Signalisation;

import static ru.sbt.mipt.oop.signalisation.SignalStateName.*;

public class SignalisationEventHandlerRunnerDecorator extends BaseEventHandlerRunnerDecorator {
    private final SMSSender smsSender;

    public SignalisationEventHandlerRunnerDecorator(EventHandlerRunner eventHandlerRunner, SMSSender smsSender) {
        super(eventHandlerRunner);
        this.smsSender = smsSender;
    }

    @Override
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
        if (event instanceof SimpleSensorEvent) {
            // simple sensor event
            Signalisation signalisation = smartHome.getSignalisation();
            SignalStateName signalStateName = signalisation.getState().getName();
            if (signalStateName.equals(STATE_ALARMED)) {
                smsSender.sendSMS(event.toString());
            } else if (signalStateName.equals(STATE_ACTIVATED)) {
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
