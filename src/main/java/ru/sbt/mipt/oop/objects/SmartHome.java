package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.signalisation.DeactivatedSignalState;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private final Collection<Room> rooms;
    private SignalisationImpl signalisation;

    public SmartHome() {
        rooms = new ArrayList<>();
        signalisation = new SignalisationImpl();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        this.signalisation = new SignalisationImpl();
    }

    public SmartHome(Collection<Room> rooms, SignalisationImpl signalisation) {
        this.rooms = rooms;
        this.signalisation = signalisation;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    // for signalisation decorator & event handlers, also tests
    public SignalisationImpl getSignalisation() {
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
