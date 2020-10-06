package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.io.SmartHomeDataOutput;
import ru.sbt.mipt.oop.io.SmartHomeDataOutputJSON;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) {
        Room kitchen = new Room(
                Arrays.asList(
                        new Light("1", false, "kitchen"),
                        new Light("2", true, "kitchen")),
                Arrays.asList(new Door(false, "1", "kitchen")),
                "kitchen"
        );
        Room bathroom = new Room(
                Arrays.asList(new Light("3", true, "bathroom")),
                Arrays.asList(new Door(false, "2", "bathroom")),
                "bathroom");
        Room bedroom = new Room(
                Arrays.asList(
                        new Light("4", false, "bedroom"),
                        new Light("5", false, "bedroom"),
                        new Light("6", false, "bedroom")),
                Arrays.asList(
                        new Door(true, "3", "bedroom")),
                "bedroom");
        Room hall = new Room(
                Arrays.asList(
                        new Light("7", false, "hall"),
                        new Light("8", false, "hall"),
                        new Light("9", false, "hall")),
                Arrays.asList(new Door(false, "4", "hall")),
                "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        SmartHomeDataOutput smartHomeOutput = new SmartHomeDataOutputJSON("output.js");
        smartHomeOutput.writeSmartHomeData(smartHome);
    }
}
