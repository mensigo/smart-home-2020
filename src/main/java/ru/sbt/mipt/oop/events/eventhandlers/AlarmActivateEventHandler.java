package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.Signalisation;

<<<<<<< HEAD
=======
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_ACTIVATE;

>>>>>>> hometask-2
public class AlarmActivateEventHandler implements EventHandler {

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
<<<<<<< HEAD
        if (!(event instanceof SignalisationSensorEvent)) {
            throw new RuntimeException("AlarmActivateEventHandler::handleEvent(..) parameter @event type is not SignalisationSensorEvent.");
        }
        SignalisationSensorEvent signalEvent = (SignalisationSensorEvent) event;
        Signalisation signalisation = smartHome.getSignalisation();
        signalisation.activate(signalEvent.getEnteredCode());
=======
        if (event instanceof SignalisationSensorEvent && event.getType().equals(ALARM_ACTIVATE)) {
            SignalisationSensorEvent signalEvent = (SignalisationSensorEvent) event;
            Signalisation signalisation = smartHome.getSignalisation();
            signalisation.activate(signalEvent.getEnteredCode());
        }
>>>>>>> hometask-2
    }
}
