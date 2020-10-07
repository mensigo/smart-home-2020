package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.objects.DoorActionable;
import ru.sbt.mipt.oop.objects.RoomActionable;
import ru.sbt.mipt.oop.objects.SmartHomeActionable;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class SensorEvent {
    private final SensorEventType type;
    private final String objectId;
    private final boolean isQueit;

    public SensorEvent(SensorEventType type, String objectId, boolean isQueit) {
        this.type = type;
        this.objectId = objectId;
        this.isQueit = isQueit;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    public boolean getQueit() { return isQueit; }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                ", isQuiet=" + isQueit +
                '}';
    }

    public boolean isSendCommandNeedyEvent(SmartHomeActionable smartHome) {
        return isDoorClosedInHallSpecialEvent(smartHome);
        // more special events can be added here
    }

    public boolean isDoorClosedInHallSpecialEvent(SmartHomeActionable smartHome) {
        if (type == DOOR_CLOSE) {
            for (RoomActionable room : smartHome.getRooms())
                for (DoorActionable door : room.getDoors()) {
                    if (door.getId().equals(objectId) && room.getName().equals("hall")) {
                        return true;
                    }
                }
        }
        return false;
    }

    public boolean isLightUsualEvent() {
        return (type == LIGHT_ON || type == LIGHT_OFF);
    }  // usual #1

    public boolean isDoorUsualEvent() { return (type == DOOR_OPEN || type == DOOR_CLOSE);  // usual #2
    }
}