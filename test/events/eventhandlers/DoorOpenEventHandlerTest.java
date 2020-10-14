package events.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.eventhandlers.DoorOpenEventHandler;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class DoorOpenEventHandlerTest {

    @Test
    void tryToOpenDoorSucceedWhenDoorIsClosed() {
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
        SimpleSensorEvent event = new SimpleSensorEvent(DOOR_OPEN, "2", false);
        CommandSender commandSender = new CommandSenderImpl();
        EventHandler smartDoorEventHandler = new DoorOpenEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOpened = true;
        assertEquals(expectedIsOpened, doors.get(1).isOpen());
    }

    @Test
    void tryToOpenDoorDoNothingWhenDoorIsOpened() {
        // given
        String roomName = "bathroom";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Arrays.asList(
                new Door(true, "1"),
                new Door(true, "2"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SmartHome smartHome = new SmartHome(rooms);
        SimpleSensorEvent event = new SimpleSensorEvent(DOOR_OPEN, "2", false);
        CommandSender commandSender = new CommandSenderImpl();
        EventHandler smartDoorEventHandler = new DoorOpenEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOpened = true;
        assertEquals(expectedIsOpened, doors.get(1).isOpen());
    }
}
