package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.eventhandlers.SmartHomeEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void handleUsualEvent(SensorEvent event) {
        SmartHomeEventHandler smartHomeEventHandler = new SmartHomeEventHandler();
        smartHomeEventHandler.handleEvent(event, this);
    }
}
