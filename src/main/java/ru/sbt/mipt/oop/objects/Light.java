package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class Light {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public void handleUsualEvent(SensorEvent event, String roomName) {
        if (event.getType() == LIGHT_ON) {
            isOn = true;
            System.out.println("Light " + id + " in room " + roomName + " was turned on.");
        } else {
            isOn = false;
            System.out.println("Light " + id + " in room " + roomName + " was turned off.");
        }
    }

    public void handleUsualEventQuietly(SensorEvent event) {
        if (event.getType() == LIGHT_ON) {
            isOn = true;
        } else {
            isOn = false;
        }
    }

    public String getId() {
        return id;
    }

    /*public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }*/
}
