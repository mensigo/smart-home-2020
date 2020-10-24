package ru.sbt.mipt.oop.events.decorators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
<<<<<<< HEAD
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerChooser;
=======
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
>>>>>>> hometask-2
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.ActivatedSignalState;
import ru.sbt.mipt.oop.signalisation.AlarmedSignalState;
import ru.sbt.mipt.oop.signalisation.DeactivatedSignalState;
import ru.sbt.mipt.oop.signalisation.Signalisation;

public class SignalisationEventChooserDecorator extends EventChooserDecorator {
    private boolean isSend;
<<<<<<< HEAD

    public SignalisationEventChooserDecorator(EventHandlerChooser eventScenarioChooser) {
=======
    public SignalisationEventChooserDecorator(EventHandlerRunner eventScenarioChooser) {
>>>>>>> hometask-2
        super(eventScenarioChooser);
    }

    @Override
<<<<<<< HEAD
    public void chooseAndRunHandler(SensorEvent event, SmartHome smartHome) {
=======
    public void runHandlers(SensorEvent event, SmartHome smartHome) {
>>>>>>> hometask-2
        if (!(event instanceof SignalisationSensorEvent)) {
            // simple sensor event
            Signalisation signalisation = smartHome.getSignalisation();
            if (signalisation.getState().equals(AlarmedSignalState.stateName)) {
                sendSMS(event);
            } else if (signalisation.getState().equals(ActivatedSignalState.stateName)) {
                signalisation.alarm();
                sendSMS(event);
            } else {
<<<<<<< HEAD
                super.chooseAndRunHandler(event, smartHome);
            }
        } else {
            // signalisation event
            super.chooseAndRunHandler(event, smartHome);
=======
                super.runHandlers(event, smartHome);
            }
        } else {
            // signalisation event
            super.runHandlers(event, smartHome);
>>>>>>> hometask-2
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
