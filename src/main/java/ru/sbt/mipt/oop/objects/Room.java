package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;

import java.util.Collection;

public class Room implements Actionable {
    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
        setThisPlaceToObjectsInside();
    }

    public String getName() { return name; }

    @Override
    public void execute(Action action) {
        action.act(this);
        for (Light light : lights) {
            light.execute(action);
        }
        for (Door door : doors) {
            door.execute(action);
        }
    }

    // used to give Room link to all objects inside when building SmartHome
    private void setThisPlaceToObjectsInside() {
        for (Light light : lights) {
            light.setPlace(this);
        }
        for (Door door : doors) {
            door.setPlace(this);
        }
    }
}
