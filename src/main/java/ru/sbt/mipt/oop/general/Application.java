package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws Exception {
        // считываем состояние дома из источника
        SmartHomeDataSource smartHomeDataSource = new SmartHomeDataSourceJSON("smart-home-1.js");
        SmartHome smartHome = smartHomeDataSource.getSmartHomeData();
        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.isLightEvent()) {
                // событие от источника света
                for (Room room : smartHome.getRooms()) {
                    room.handleLightEvent(event);
                }
            }
            if (event.isDoorEvent()) {
                // событие от двери
                if (event.isDoorClosedInHallEvent()) {
                    smartHome.DoorClosedInHallEvent();
                }
                else {
                    for (Room room : smartHome.getRooms()) {
                        room.handleDoorEvent(event);
                    }
                }
            }
            event = getNextSensorEvent();
        }
    }

    static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
