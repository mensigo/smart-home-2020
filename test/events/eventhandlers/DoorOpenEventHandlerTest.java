package events.eventhandlers;

import org.junit.jupiter.api.BeforeEach;
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
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class DoorOpenEventHandlerTest {
    private List<Light> lights;
    private List<Door> doors;
    private SmartHome smartHome;
    private CommandSender commandSender;

    @BeforeEach
    public void prepareSmartHome() {
        String roomName = "kitchen";
        lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        doors = Arrays.asList(
                new Door(true, "1"),
                new Door(false, "2")
        );
        Room room = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(room);
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        smartHome = new SmartHome(rooms, signalisation);
        commandSender = new CommandSenderImpl();
    }

    @Test
    void tryToOpenDoorSucceedWhenDoorIsClosed() {
        // given
        SimpleSensorEvent event = new SimpleSensorEvent(DOOR_OPEN, "2", false);
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
        SimpleSensorEvent event = new SimpleSensorEvent(DOOR_OPEN, "1", false);
        EventHandler smartDoorEventHandler = new DoorOpenEventHandler(commandSender);
        // when
        smartDoorEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOpened = true;
        assertEquals(expectedIsOpened, doors.get(0).isOpen());
    }
}
