package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.io.SmartHomeDataInput;
import ru.sbt.mipt.oop.io.SmartHomeDataInputJSON;

public class Application {

    public static void main(String... args) throws Exception {
        // get smartHome state from some source
        SmartHomeDataInput smartHomeInput = new SmartHomeDataInputJSON("smart-home-1.js");
        SmartHome smartHome = smartHomeInput.getData();
        // setup command module
        TotalCommander commander = new TotalCommander();
        // start events handling cycle
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.isSendCommandNeedyEvent()) {
                // "special" events: DoorClosedInHall etc.
                commander.handleSpecialEvent(event, smartHome);
            }
            else {
                // "usual" events: Light, Door events etc.
                smartHome.handleUsualEvent(event);
            }
            event = getNextSensorEvent();
        }
    }

    static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) { return null; } // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (SensorEventType.values().length * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
