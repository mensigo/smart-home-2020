package ru.sbt.mipt.oop.events;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

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

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public boolean isSendCommandNeedyEvent() { return (type == DOOR_CLOSED_IN_HALL); }  // special events

    public boolean isDoorClosedInHallSpecialEvent() { return (type == DOOR_CLOSED_IN_HALL); }  // special #1

    public boolean isLightUsualEvent() {
        return (type == LIGHT_ON || type == LIGHT_OFF);
    }  // usual #1

    public boolean isDoorUsualEvent() { return (type == DOOR_OPEN || type == DOOR_CLOSED);  // usual #2
    }


}
