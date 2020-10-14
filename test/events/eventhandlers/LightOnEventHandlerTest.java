package events.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.eventhandlers.LightOnEventHandler;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class LightOnEventHandlerTest {

    @Test
    void tryToTurnOnLightSucceedWhenLightIsOff() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SmartHome smartHome = new SmartHome(rooms);
        SimpleSensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        CommandSender commandSender = new CommandSenderImpl();
        EventHandler smartLightEventHandler = new LightOnEventHandler(commandSender);
        // when
        smartLightEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOn = true;
        assertEquals(expectedIsOn, lights.get(0).isOn());
    }

    @Test
    void tryToTurnOnLightDoNothingWhenLightIsOn() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", true),
                new Light("2", false));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SmartHome smartHome = new SmartHome(rooms);
        SimpleSensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        CommandSender commandSender = new CommandSenderImpl();
        EventHandler smartLightEventHandler = new LightOnEventHandler(commandSender);
        // when
        smartLightEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOn = true;
        assertEquals(expectedIsOn, lights.get(0).isOn());
    }
}
