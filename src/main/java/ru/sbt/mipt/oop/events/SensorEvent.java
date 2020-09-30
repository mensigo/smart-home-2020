package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SensorEvent {
    private final SensorEventType type;
    private final String objectId;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    public boolean isLightEvent() {
        return (type == LIGHT_ON || type == LIGHT_OFF);
    }

    public boolean isDoorEvent() {
        return (type == DOOR_OPEN || type == DOOR_CLOSED
                || type == DOOR_CLOSED_IN_HALL);
    }

    public boolean isDoorClosedInHallEvent() {
        return (type == DOOR_CLOSED_IN_HALL);
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
