package events.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.DoorCloseEventHandler;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class DoorCloseEventHandlerTest {

    @Test
    void tryToCloseDoorSucceedWhenDoorIsNotInHallAndIsOpened() {
        // given
        String roomName = "bathroom";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Arrays.asList(
                new Door(true, "1"),
                new Door(false, "2"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SmartHome smartHome = new SmartHome(rooms);
        SimpleSensorEvent event = new SimpleSensorEvent(DOOR_CLOSE, "1", false);
        CommandSender commandSender = new CommandSenderImpl();
        EventHandler smartDoorEventHandler = new DoorCloseEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOpened = false;
        assertEquals(expectedIsOpened, doors.get(0).isOpen());
    }

    @Test
    void tryToCloseDoorDoNothingWhenDoorIsNotInHallAndIsClosed() {
        // given
        String roomName = "bathroom";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Arrays.asList(
                new Door(false, "1"),
                new Door(false, "2"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SmartHome smartHome = new SmartHome(rooms);
        SimpleSensorEvent event = new SimpleSensorEvent(DOOR_CLOSE, "1", false);
        CommandSender commandSender = new CommandSenderImpl();
        EventHandler smartDoorEventHandler = new DoorCloseEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOpened = false;
        assertEquals(expectedIsOpened, doors.get(0).isOpen());
    }

    @Test
    void tryToCloseDoorSucceedAndSendCommandsToTurnOffAllLightsWhenDoorIsInHallAndIsOpened() {
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
        EventHandler smartDoorEventHandler = new DoorCloseEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOpened = false;
        assertEquals(expectedIsOpened, doors.get(0).isOpen());
    }

    @Test
    void tryToCloseDoorDoNothingWhenDoorIsInHallAndIsClosed() {
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
        EventHandler smartDoorEventHandler = new DoorCloseEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOpened = false;
        assertEquals(expectedIsOpened, doors.get(0).isOpen());
    }
}
