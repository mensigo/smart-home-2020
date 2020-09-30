package ru.sbt.mipt.oop.io;

import ru.sbt.mipt.oop.general.SmartHome;

public interface SmartHomeDataOutput {
    void pushData(SmartHome smartHome) throws Exception;
}
