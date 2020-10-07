package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.eventhandlers.SmartLightEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;

public class LightActionable implements Actionable {
    private boolean isOn;
    private final String id;
    private String placeName;

    public LightActionable(String id, boolean isOn, String placeName) {
        this.id = id;
        this.isOn = isOn;
        this.placeName = placeName;
    }

    public String getId() {
        return id;
    }

    public String getPlaceName() { return placeName; }

    public boolean isOn() { return isOn; }

    public void handleUsualEvent(SensorEvent event) {
        SmartLightEventHandler smartLightEventHandler = new SmartLightEventHandler();
        smartLightEventHandler.handleEvent(event, this);
    }

    public void setOn(boolean on) { isOn = on; }

    @Override
    public void execute(Action action) {
        action.executeAction(this);
    }
}
