package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerChooser;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.ActivatedSignalState;
import ru.sbt.mipt.oop.signalisation.AlarmedSignalState;
import ru.sbt.mipt.oop.signalisation.DeactivatedSignalState;
import ru.sbt.mipt.oop.signalisation.Signalisation;

public class SignalisationEventChooserDecorator extends EventChooserDecorator {
    private boolean isSend;

    public SignalisationEventChooserDecorator(EventHandlerChooser eventScenarioChooser) {
        super(eventScenarioChooser);
    }

    @Override
    public void chooseAndRunHandler(SensorEvent event, SmartHome smartHome) {
        if (!(event instanceof SignalisationSensorEvent)) {
            // simple sensor event
            Signalisation signalisation = smartHome.getSignalisation();
            if (signalisation.getState().equals(AlarmedSignalState.stateName)) {
                sendSMS(event);
            } else if (signalisation.getState().equals(ActivatedSignalState.stateName)) {
                signalisation.alarm();
                sendSMS(event);
            } else {
                super.chooseAndRunHandler(event, smartHome);
            }
        } else {
            // signalisation event
            super.chooseAndRunHandler(event, smartHome);
            if (smartHome.getSignalisation().getState().equals(DeactivatedSignalState.stateName)) {
                // reset signalisation
                setSend(false);
            }
        }
    }

    private void setSend(boolean isSend) { this.isSend = isSend; }

    private void sendSMS(SensorEvent event) {
        if (!isSend) {
            System.out.println("Sending sms.. // " + event.toString());
            setSend(true);
        }
    }
}
