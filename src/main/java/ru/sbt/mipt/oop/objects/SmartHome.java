package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.signalisation.Signalisation;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private final Collection<Room> rooms;
    private final Signalisation signalisation;

    public SmartHome(Signalisation signalisation) {
        this.rooms = new ArrayList<>();
        this.signalisation = signalisation;
    }

    public SmartHome(Collection<Room> rooms, Signalisation signalisation) {
        this.rooms = rooms;
        this.signalisation = signalisation;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    // used in decorator, handlers
    public Signalisation getSignalisation() {
        return signalisation;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
