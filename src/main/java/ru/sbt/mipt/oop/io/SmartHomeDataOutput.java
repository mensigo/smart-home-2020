package ru.sbt.mipt.oop.io;

import ru.sbt.mipt.oop.objects.SmartHomeActionable;

public interface SmartHomeDataOutput {
    void writeSmartHomeData(SmartHomeActionable smartHome);
}