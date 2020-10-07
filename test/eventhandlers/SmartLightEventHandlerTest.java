package eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventhandlers.SmartLightEventHandler;
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

public class SmartLightEventHandlerTest {

    @Test
    void turnOnLightWhenLightIsOff() {
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
        SmartObjectEventHandler smartLightEventHandler = new SmartLightEventHandler();
        List<LightActionable> lights = new ArrayList<>(kitchen.getLights());
        LightActionable lightToTurnOn = lights.get(0);
        // when
        smartLightEventHandler.handleEvent(event, lightToTurnOn);
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
    void turnOffLightWhenLightIsOn() {
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
        String id = "2";
        SensorEvent event = new SensorEvent(LIGHT_OFF, id, false);
        SmartObjectEventHandler smartLightEventHandler = new SmartLightEventHandler();
        List<LightActionable> lights = new ArrayList<>(kitchen.getLights());
        LightActionable lightToTurnOff = lights.get(1);
        // when
        smartLightEventHandler.handleEvent(event, lightToTurnOff);
        // then
        boolean expectedIsOn = false;
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
}
