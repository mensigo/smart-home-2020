package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.Signalisation;

public class AlarmActivateEventHandler implements EventHandler {

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (!(event instanceof SignalisationSensorEvent)) {
            throw new RuntimeException("AlarmActivateEventHandler::handleEvent(..) parameter @event type is not SignalisationSensorEvent.");
        }
        SignalisationSensorEvent signalEvent = (SignalisationSensorEvent) event;
        Signalisation signalisation = smartHome.getSignalisation();
        signalisation.activate(signalEvent.getEnteredCode());
    }
}
