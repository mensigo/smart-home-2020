package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface CommandSender {
    void handleSpecialEvent(SensorEvent event, Object object);
}
