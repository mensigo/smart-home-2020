package ru.sbt.mipt.oop.lib.coolcompany;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;

import java.util.Map;

public class CCEventTypeTranslatorImpl implements CCEventTypeTranslator {
    private final Map<String, SensorEventType> ccEventTypeToSensorEventTypeMap;

    public CCEventTypeTranslatorImpl(Map<String, SensorEventType> ccEventTypeToSensorEventTypeMap) {
        this.ccEventTypeToSensorEventTypeMap = ccEventTypeToSensorEventTypeMap;
    }

    @Override
    public SensorEvent translate(CCSensorEvent ccEvent) {
        if (ccEvent == null || !ccEventTypeToSensorEventTypeMap.containsKey(ccEvent.getEventType())) return null;
        SensorEventType sensorEventType = ccEventTypeToSensorEventTypeMap.get(ccEvent.getEventType());
        return new SimpleSensorEvent(sensorEventType, ccEvent.getObjectId(), false);
    }
}
