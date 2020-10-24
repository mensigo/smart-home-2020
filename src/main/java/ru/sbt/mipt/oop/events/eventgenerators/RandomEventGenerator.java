package ru.sbt.mipt.oop.events.eventgenerators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;

import java.util.Arrays;

<<<<<<< HEAD
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_DEACTIVATE;
=======
import static ru.sbt.mipt.oop.events.SensorEventType.*;
>>>>>>> hometask-2

public class RandomEventGenerator implements EventGenerator {

    @Override
    public SensorEvent provideNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) { return null; } // null means end of event stream
        String objectId = "" + ((int) (10 * Math.random()));
        SensorEventType sensorEventType = SensorEventType.values()[(int) (SensorEventType.values().length * Math.random())];
        if (sensorEventType == ALARM_ACTIVATE || sensorEventType == ALARM_DEACTIVATE) {
            String enteredCode = Arrays.asList("0000", "1234").get((int) (2 * Math.random()));
            return new SignalisationSensorEvent(sensorEventType, enteredCode);
        }
        return new SimpleSensorEvent(sensorEventType, objectId, false);
    }
}