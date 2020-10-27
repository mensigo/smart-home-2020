package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;
    private Actionable place;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    // used in DoorCloseInHall action
    public Actionable getPlace() { return place; }

    public boolean isOpen() { return isOpen; }

    // used in Room
    public void  setPlace(Actionable place) {
        this.place = place;
    }

    // used in Actions
    public void setOpen(boolean open) { isOpen = open; }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
