package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.Signalisation;

import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDeactivateEventHandler implements EventHandler {

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event instanceof SignalisationSensorEvent & event.getType().equals(ALARM_DEACTIVATE)) {
            SignalisationSensorEvent signalEvent = (SignalisationSensorEvent) event;
            Signalisation signalisation = smartHome.getSignalisation();
            signalisation.deactivate(signalEvent.getEnteredCode());
        }
    }
}
