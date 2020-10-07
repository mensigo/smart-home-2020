package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.io.SmartHomeDataOutput;
import ru.sbt.mipt.oop.io.SmartHomeDataOutputJSON;
import ru.sbt.mipt.oop.objects.DoorActionable;
import ru.sbt.mipt.oop.objects.LightActionable;
import ru.sbt.mipt.oop.objects.RoomActionable;
import ru.sbt.mipt.oop.objects.SmartHomeActionable;

import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) {
        RoomActionable kitchen = new RoomActionable(
                Arrays.asList(
                        new LightActionable("1", false, "kitchen"),
                        new LightActionable("2", true, "kitchen")),
                Arrays.asList(new DoorActionable(false, "1", "kitchen")),
                "kitchen"
        );
        RoomActionable bathroom = new RoomActionable(
                Arrays.asList(new LightActionable("3", true, "bathroom")),
                Arrays.asList(new DoorActionable(false, "2", "bathroom")),
                "bathroom");
        RoomActionable bedroom = new RoomActionable(
                Arrays.asList(
                        new LightActionable("4", false, "bedroom"),
                        new LightActionable("5", false, "bedroom"),
                        new LightActionable("6", false, "bedroom")),
                Arrays.asList(
                        new DoorActionable(true, "3", "bedroom")),
                "bedroom");
        RoomActionable hall = new RoomActionable(
                Arrays.asList(
                        new LightActionable("7", false, "hall"),
                        new LightActionable("8", false, "hall"),
                        new LightActionable("9", false, "hall")),
                Arrays.asList(new DoorActionable(false, "4", "hall")),
                "hall");
        SmartHomeActionable smartHome = new SmartHomeActionable(Arrays.asList(kitchen, bathroom, bedroom, hall));
        SmartHomeDataOutput smartHomeOutput = new SmartHomeDataOutputJSON("output.js");
        smartHomeOutput.writeSmartHomeData(smartHome);
    }
}
