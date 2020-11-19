package events.eventhandlers;

import org.junit.jupiter.api.BeforeEach;
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
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class LightOnEventHandlerTest {
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
    void tryToTurnOnLightSucceedWhenLightIsOff() {
        // given
        SimpleSensorEvent event = new SimpleSensorEvent(LIGHT_ON, "2", false);
        EventHandler smartLightEventHandler = new LightOnEventHandler(commandSender);
        // when
        smartLightEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOn = true;
        assertEquals(expectedIsOn, lights.get(1).isOn());
    }

    @Test
    void tryToTurnOnLightDoNothingWhenLightIsOn() {
        // given
        SimpleSensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandler smartLightEventHandler = new LightOnEventHandler(commandSender);
        // when
        smartLightEventHandler.handleEvent(event, smartHome);
        // then
        boolean expectedIsOn = true;
        assertEquals(expectedIsOn, lights.get(0).isOn());
    }
}
