package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.eventhandlers.SmartLightEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;

public class Light {
    private boolean isOn;
    private final String id;
    private String placeName;

    public Light(String id, boolean isOn, String placeName) {
        this.id = id;
        this.isOn = isOn;
        this.placeName = placeName;
    }

    public String getId() {
        return id;
    }

    public String getPlaceName() { return placeName; }

    public void handleUsualEvent(SensorEvent event) {
        SmartLightEventHandler smartLightEventHandler = new SmartLightEventHandler();
        smartLightEventHandler.handleEvent(event, this);
    }

    public void setOn(boolean on) { isOn = on; }
}
