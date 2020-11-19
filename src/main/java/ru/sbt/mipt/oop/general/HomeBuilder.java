package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.io.SmartHomeDataOutput;
import ru.sbt.mipt.oop.io.JSONSmartHomeDataOutput;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;

public class HomeBuilder {

    public static void main(String[] args) {
        SmartHome smartHome = buildSampleSmartHome();
        SmartHomeDataOutput smartHomeOutput = new JSONSmartHomeDataOutput("output.js");
        smartHomeOutput.writeSmartHomeData(smartHome);
    }

    public static SmartHome buildSampleSmartHome() {
        Room kitchen = new Room(
                Arrays.asList(
                        new Light("1", false),
                        new Light("2", true)
                ),
                Collections.singletonList(new Door(false, "1")),
                "kitchen"
        );
        Room bathroom = new Room(
                Collections.singletonList(new Light("3", true)),
                Collections.singletonList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(
                Arrays.asList(
                        new Light("4", false),
                        new Light("5", false),
                        new Light("6", false)),
                Collections.singletonList(
                        new Door(true, "3")),
                "bedroom");
        Room hall = new Room(
                Arrays.asList(
                        new Light("7", false),
                        new Light("8", false),
                        new Light("9", false)),
                Collections.singletonList(new Door(true, "4")),
                "hall");
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall), signalisation);
    }
}
