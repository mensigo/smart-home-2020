package ru.sbt.mipt.oop.events;

public class SimpleSensorEvent implements SensorEvent {
    private final SensorEventType type;
    private final String objectId;
    private final boolean isQuiet;

    public SimpleSensorEvent(SensorEventType type, String objectId, boolean isQuiet) {
        this.type = type;
        this.objectId = objectId;
        this.isQuiet = isQuiet;
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
    public boolean isQuiet() { return isQuiet; }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                ", isQuiet=" + isQuiet +
                '}';
    }
}
