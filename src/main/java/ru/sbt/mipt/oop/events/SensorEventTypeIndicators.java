package ru.sbt.mipt.oop.events;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

// EventHandlerChooserImpl knows every event type and chooses accordingly (without event grouping).
@Deprecated
public class SensorEventTypeIndicators {

    public boolean isLightEvent(SensorEvent event) {
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

    public boolean isDoorEvent(SensorEvent event) {
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSE);
    }

    public boolean isSignalisationEvent(SensorEvent event) {
        return (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE) ;
    }
}
