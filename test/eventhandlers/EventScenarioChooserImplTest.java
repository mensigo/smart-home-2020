package eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.eventhandlers.EventScenarioChooser;
import ru.sbt.mipt.oop.eventhandlers.EventScenarioChooserImpl;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.DoorActionable;
import ru.sbt.mipt.oop.objects.LightActionable;
import ru.sbt.mipt.oop.objects.RoomActionable;
import ru.sbt.mipt.oop.objects.SmartHomeActionable;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class EventScenarioChooserImplTest {

    @Test
    void chooseAndImplementUsualEventScenarioTurnOnLightWhenLightIsOff() {
        // given
        String roomName = "kitchen";
        RoomActionable kitchen = new RoomActionable(
                Arrays.asList(
                        new LightActionable("1", false, roomName),
                        new LightActionable("2", true, roomName)),
                Collections.singletonList(new DoorActionable(false, "1", roomName)),
                roomName
        );
        SmartHomeActionable smartHome = new SmartHomeActionable(Collections.singletonList(kitchen));
        String id = "1";
        SensorEvent event = new SensorEvent(LIGHT_ON, id, false);
        EventScenarioChooser eventScenarioChooser = new EventScenarioChooserImpl();
        CommandSender commandSender = new CommandSenderImpl();
        // when
        eventScenarioChooser.chooseAndImplementScenario(event, smartHome, commandSender);
        // then
        boolean expectedIsOn = true;
        for (RoomActionable room : smartHome.getRooms()) {
            if (room.getName().equals(roomName)) {
                for (LightActionable light : room.getLights()) {
                    if (light.getId().equals(id)) {
                        assertEquals(expectedIsOn, light.isOn());
                        break;
                    }
                }
            }
        }
    }

    @Test
    void chooseAndImplementSpecialEventScenarioCloseDoorTurnOffLightsWhenDoorInHall() {
        String hallName = "hall";
        RoomActionable hall = new RoomActionable(
                Arrays.asList(
                        new LightActionable("1", false, hallName),
                        new LightActionable("2", true, hallName)),
                Collections.singletonList(new DoorActionable(true, "1", hallName)),
                hallName
        );
        String kitchenName = "kitchen";
        RoomActionable kitchen = new RoomActionable(
                Arrays.asList(
                        new LightActionable("3", true, kitchenName),
                        new LightActionable("4", true, kitchenName)),
                Collections.singletonList(new DoorActionable(true, "2", kitchenName)),
                kitchenName
        );
        SmartHomeActionable smartHome = new SmartHomeActionable(Arrays.asList(hall, kitchen));
        String id = "1";
        SensorEvent event = new SensorEvent(DOOR_CLOSE, id, false);
        EventScenarioChooser eventScenarioChooser = new EventScenarioChooserImpl();
        CommandSender commandSender = new CommandSenderImpl();
        // when
        eventScenarioChooser.chooseAndImplementScenario(event, smartHome, commandSender);
        // then
        // the door is closed
        boolean expectedIsOpened = false;
        for (RoomActionable room : smartHome.getRooms()) {
            if (room.getName().equals(hallName)) {
                for (DoorActionable door : room.getDoors()) {
                    if (door.getId().equals(id)) {
                        assertEquals(expectedIsOpened, door.isOpen());
                        break;
                    }
                }
            }
        }
        // all lights are off
        boolean expectedIsOn = false;
        for (RoomActionable room : smartHome.getRooms()) {
            for (LightActionable light : room.getLights()) {
                assertEquals(expectedIsOn, light.isOn());
            }
        }
    }
}
