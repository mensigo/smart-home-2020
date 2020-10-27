package ru.sbt.mipt.oop.lib.coolcompany;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.events.SensorEvent;

public interface CCEventTypeTranslator {
    SensorEvent translate(CCSensorEvent ccEvent);
}
