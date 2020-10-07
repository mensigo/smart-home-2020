package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.eventhandlers.SmartHomeEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHomeActionable implements Actionable {
    private Collection<RoomActionable> rooms;

    public SmartHomeActionable() {
        rooms = new ArrayList<>();
    }

    public SmartHomeActionable(Collection<RoomActionable> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(RoomActionable room) {
        rooms.add(room);
    }

    public Collection<RoomActionable> getRooms() {
        return rooms;
    }

    public void handleUsualEvent(SensorEvent event) {
        SmartHomeEventHandler smartHomeEventHandler = new SmartHomeEventHandler();
        smartHomeEventHandler.handleEvent(event, this);
    }

    @Override
    public void execute(Action action) {
        for (RoomActionable room : rooms) {
            room.execute(action);
        }
    }
}
