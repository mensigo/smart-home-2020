package ru.sbt.mipt.oop.events.eventgenerators;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface EventGenerator {

    SensorEvent provideNextEvent();
}
