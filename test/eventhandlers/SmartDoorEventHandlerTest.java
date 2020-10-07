package eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventhandlers.SmartDoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.SmartObjectEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.DoorActionable;
import ru.sbt.mipt.oop.objects.LightActionable;
import ru.sbt.mipt.oop.objects.RoomActionable;
import ru.sbt.mipt.oop.objects.SmartHomeActionable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class SmartDoorEventHandlerTest {

    @Test
    void CloseDoorWhenDoorIsOpened() {
        // given
        String roomName = "bathroom";
        RoomActionable bathroom = new RoomActionable(
                Arrays.asList(
                        new LightActionable("1", false, roomName),
                        new LightActionable("2", true, roomName)),
                Arrays.asList(
                        new DoorActionable(true, "1", roomName),
                        new DoorActionable(false, "2", roomName)),
                roomName
        );
        SmartHomeActionable smartHome = new SmartHomeActionable(Collections.singletonList(bathroom));
        String id = "1";
        SensorEvent event = new SensorEvent(DOOR_CLOSE, id, false);
        SmartObjectEventHandler smartDoorEventHandler = new SmartDoorEventHandler();
        List<DoorActionable> doors = new ArrayList<>(bathroom.getDoors());
        DoorActionable doorToClose = doors.get(0);
        // when
        smartDoorEventHandler.handleEvent(event, doorToClose);
        // then
        boolean expectedIsOpened = false;
        for (RoomActionable room : smartHome.getRooms()) {
            if (room.getName().equals(roomName)) {
                for (DoorActionable door : room.getDoors()) {
                    if (door.getId().equals(id)) {
                        assertEquals(expectedIsOpened, door.isOpen());
                        break;
                    }
                }
            }
        }
    }

    @Test
    void OpenDoorWhenDoorIsClosed() {
        // given
        String roomName = "bathroom";
        RoomActionable bathroom = new RoomActionable(
                Arrays.asList(
                        new LightActionable("1", false, roomName),
                        new LightActionable("2", true, roomName)),
                Arrays.asList(
                        new DoorActionable(true, "1", roomName),
                        new DoorActionable(false, "2", roomName)),
                roomName
        );
        SmartHomeActionable smartHome = new SmartHomeActionable(Collections.singletonList(bathroom));
        String id = "2";
        SensorEvent event = new SensorEvent(DOOR_OPEN, id, false);
        SmartObjectEventHandler smartDoorEventHandler = new SmartDoorEventHandler();
        List<DoorActionable> doors = new ArrayList<>(bathroom.getDoors());
        DoorActionable doorToOpen = doors.get(1);
        // when
        smartDoorEventHandler.handleEvent(event, doorToOpen);
        // then
        boolean expectedIsOpened = true;
        for (RoomActionable room : smartHome.getRooms()) {
            if (room.getName().equals(roomName)) {
                for (DoorActionable door : room.getDoors()) {
                    if (door.getId().equals(id)) {
                        assertEquals(expectedIsOpened, door.isOpen());
                        break;
                    }
                }
            }
        }
    }
}
