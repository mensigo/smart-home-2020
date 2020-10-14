package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;
    private String placeName;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPlaceName() { return placeName; }

    public boolean isOpen() { return isOpen; }

    // used in Room
    public void  setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    // used in Actions
    public void setOpen(boolean open) { isOpen = open; }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
