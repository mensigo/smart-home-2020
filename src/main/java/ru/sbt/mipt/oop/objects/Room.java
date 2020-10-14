package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;

import java.util.Collection;

public class Room implements Actionable {
    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        setName(name);
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

    // used to give Room.name to all objects inside when building SmartHome
    private void setName(String name) {
        this.name = name;
        for (Light light : lights) {
            light.setPlaceName(name);
        }
        for (Door door : doors) {
            door.setPlaceName(name);
        }
    }
}
