package ru.sbt.mipt.oop.lib.coolcompany;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.UnknownSensorEvent;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class CCEventTypeTranslatorImpl implements CCEventTypeTranslator {
    @Override
    public SensorEvent translate(CCSensorEvent ccEvent) {
        if (ccEvent == null) return null;
        switch (ccEvent.getEventType()) {
            case "LightIsOn":
                return new SimpleSensorEvent(LIGHT_ON, ccEvent.getObjectId(), false);
            case "LightIsOff":
                return new SimpleSensorEvent(LIGHT_OFF, ccEvent.getObjectId(), false);
            case "DoorIsOpen":
                return new SimpleSensorEvent(DOOR_OPEN, ccEvent.getObjectId(), false);
            case "DoorIsClosed":
                return new SimpleSensorEvent(DOOR_CLOSE, ccEvent.getObjectId(), false);
            default:
                // Incompatible events ("DoorIsLocked", "DoorIsUnlocked")
                return new UnknownSensorEvent(UNKNOWN_EVENT, ccEvent.getObjectId(), "unType=" + ccEvent.getEventType());
        }
    }
}
