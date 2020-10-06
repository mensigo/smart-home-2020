package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.eventhandlers.SmartRoomEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.Collection;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() { return doors; }

    public String getName() { return name; }

    public void handleUsualEvent(SensorEvent event) {
        SmartRoomEventHandler smartRoomEventHandler = new SmartRoomEventHandler();
        smartRoomEventHandler.handleEvent(event, this);
    }
}