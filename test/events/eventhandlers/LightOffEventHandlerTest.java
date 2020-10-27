package events.eventhandlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.events.eventhandlers.LightOffEventHandler;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class LightOffEventHandlerTest {
    private List<Light> lights;
    private List<Door> doors;
    private SmartHome smartHome;
    private CommandSender commandSender;

    @BeforeEach
    public void prepareSmartHome() {
        String roomName = "kitchen";
        lights= Arrays.asList(
                new Light("1", true),
                new Light("2", false));
        doors = Arrays.asList(
                new Door(false, "1"),
                new Door(true, "2"));
        Room room = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(room);
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        smartHome = new SmartHome(rooms, signalisation);
        commandSender = new CommandSenderImpl();
    }

    @Test
    void tryToTurnOffLightSucceedWhenLightIsOn() {
        // given
        SimpleSensorEvent event = new SimpleSensorEvent(LIGHT_OFF, "1", false);
        EventHandler smartLightEventHandler = new LightOffEventHandler(commandSender);
        // when
        smartLightEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOn = false;
        assertEquals(expectedIsOn, lights.get(0).isOn());
    }

    @Test
    void tryToTurnOffLightDoNothingWhenLightIsOff() {
        // given
        SimpleSensorEvent event = new SimpleSensorEvent(LIGHT_OFF, "2", false);
        EventHandler smartLightEventHandler = new LightOffEventHandler(commandSender);
        // when
        smartLightEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOn = false;
        assertEquals(expectedIsOn, lights.get(1).isOn());
    }
}
