package events.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.DoorCloseInHallEventHandler;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSE;

public class DoorCloseInHallEventHandlerTest {

    @Test
    void handleDoorInHallScenarioTurnOffAllLightsAndSendCommandsToThemWhenDoorIsOpened() {
        // given
        String roomName = "hall";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(new Door(true, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SmartHome smartHome = new SmartHome(rooms);
        SimpleSensorEvent event = new SimpleSensorEvent(DOOR_CLOSE, "1", false);
        CommandSender commandSender = new CommandSenderImpl();
        EventHandler smartDoorEventHandler = new DoorCloseInHallEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        assertFalse(lights.get(0).isOn());
        assertFalse(lights.get(1).isOn());
    }

    @Test
    void handleDoorInHallScenarioDoNothingWhenDoorIsClosed() {
        // given
        String roomName = "hall";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SmartHome smartHome = new SmartHome(rooms);
        SimpleSensorEvent event = new SimpleSensorEvent(DOOR_CLOSE, "1", false);
        CommandSender commandSender = new CommandSenderImpl();
        EventHandler smartDoorEventHandler = new DoorCloseInHallEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        assertFalse(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
    }
}
