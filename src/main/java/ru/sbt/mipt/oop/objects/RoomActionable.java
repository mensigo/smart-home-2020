package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.eventhandlers.SmartRoomEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.Collection;

public class RoomActionable implements Actionable {
    private Collection<LightActionable> lights;
    private Collection<DoorActionable> doors;
    private String name;

    public RoomActionable(Collection<LightActionable> lights, Collection<DoorActionable> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<LightActionable> getLights() {
        return lights;
    }

    public Collection<DoorActionable> getDoors() { return doors; }

    public String getName() { return name; }

    public void handleUsualEvent(SensorEvent event) {
        SmartRoomEventHandler smartRoomEventHandler = new SmartRoomEventHandler();
        smartRoomEventHandler.handleEvent(event, this);
    }

    @Override
    public void execute(Action action) {
        for (LightActionable light : lights) {
            light.execute(action);
        }
        for (DoorActionable door : doors) {
            door.execute(action);
        }
    }
}