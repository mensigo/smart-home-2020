package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class Door {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public void handleUsualEvent(SensorEvent event, String roomName) {
        if (event.getType() == DOOR_OPEN) {
            isOpen = true;
            System.out.println("Door " + id + " in room " + roomName + " was opened.");
        } else {
            isOpen = false;
            System.out.println("Door " + id + " in room " + roomName + " was closed.");
        }
    }

    public String getId() {
        return id;
    }

    /*public void setOpen(boolean open) {
        isOpen = open;
    }*/
}
