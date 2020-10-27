package ru.sbt.mipt.oop.io;

import ru.sbt.mipt.oop.general.HomeBuilder;
import ru.sbt.mipt.oop.objects.SmartHome;

public class CustomSmartHomeDataInput implements SmartHomeDataInput {
    @Override
    public SmartHome readSmartHomeData() {
        return HomeBuilder.buildSampleSmartHome();
    }
}
