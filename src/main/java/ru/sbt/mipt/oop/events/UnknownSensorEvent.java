package ru.sbt.mipt.oop.events;

public class UnknownSensorEvent implements SensorEvent {
    private final SensorEventType type;
    private final String objectId;
    private final String info;

    public UnknownSensorEvent(SensorEventType type, String objectId, String info) {
        this.type = type;
        this.objectId = objectId;
        this.info = info;
    }

    @Override
    public SensorEventType getType() {
        return type;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    @Override
    public boolean isQuiet() {
        return false;
    }

    public String getInfo() {
        return info;
    }
}
