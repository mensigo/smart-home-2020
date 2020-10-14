package ru.sbt.mipt.oop.events;

public interface SensorEvent {

    SensorEventType getType();

    String getObjectId();

    boolean isQuiet();

    String toString();
}
