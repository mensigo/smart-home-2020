package remotecontrol.buttons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.LightOnAllButtonCommand;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.LightOnInHallButtonCommand;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LightOnInHallButtonCommandTest {
    private List<Light> lights1, lights2;
    private SmartHome smartHome;
    private CommandSender commandSender;
    private String hallName;

    @BeforeEach
    public void prepareSmartHome() {
        hallName = "hall";
        lights1 = Arrays.asList(
                new Light("1", true),
                new Light("2", false),
                new Light("3", false)
        );
        List<Door> doors1 = Collections.singletonList(
                new Door(false, "1")
        );
        String roomName = "kitchen";
        lights2 = Arrays.asList(
                new Light("1", true),
                new Light("2", false),
                new Light("3", false)
        );
        List<Door> doors2 = Arrays.asList(
                new Door(false, "1"),
                new Door(true, "2")
        );
        Room room1 = new Room(lights1, doors1, hallName);
        Room room2 = new Room(lights2, doors2, roomName);
        List<Room> rooms = Arrays.asList(room1, room2);
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        smartHome = new SmartHome(rooms, signalisation);
        commandSender = new CommandSenderImpl();
    }

    @Test
    void turnOnAllLightsInHallAndDoNothingWithOtherLightsWhenButtonCodeIsRight() {
        // given
        String buttonCode = "A";
        ButtonCommand buttonCommand = new LightOnInHallButtonCommand(
                buttonCode,
                smartHome,
                commandSender,
                false,
                hallName);
        // when
        buttonCommand.execute(buttonCode);
        // then
        assertTrue(lights1.get(0).isOn());
        assertTrue(lights1.get(1).isOn());
        assertTrue(lights1.get(2).isOn());
        assertTrue(lights2.get(0).isOn());
        assertFalse(lights2.get(1).isOn());
        assertFalse(lights2.get(2).isOn());
    }

    @Test
    void doNothingWhenButtonCodeIsWrong() {
        // given
        String buttonCode = "A";
        ButtonCommand buttonCommand = new LightOnInHallButtonCommand(
                buttonCode,
                smartHome,
                commandSender,
                false,
                hallName);
        // when
        String anotherButtonCode = "B";
        buttonCommand.execute(anotherButtonCode);
        // then
        assertTrue(lights1.get(0).isOn());
        assertFalse(lights1.get(1).isOn());
        assertFalse(lights1.get(2).isOn());
        assertTrue(lights2.get(0).isOn());
        assertFalse(lights2.get(1).isOn());
        assertFalse(lights2.get(2).isOn());
    }
}
