package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;

public class Light implements Actionable {
    private boolean isOn;
    private final String id;
    private String placeName;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public String getId() {
        return id;
    }

    public String getPlaceName() { return placeName; }

    public boolean isOn() { return isOn; }

    // used in Room
    public void  setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    // used in Actions
    public void setOn(boolean on) { isOn = on; }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
