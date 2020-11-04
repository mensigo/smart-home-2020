package remotecontrol.buttons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.LightOnAllButtonCommand;
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

public class LightOnAllButtonCommandTest {
    private List<Light> lights;
    private SmartHome smartHome;
    private CommandSender commandSender;

    @BeforeEach
    public void prepareSmartHome() {
        String roomName = "kitchen";
        lights= Arrays.asList(
                new Light("1", true),
                new Light("2", false),
                new Light("3", false)
        );
        List<Door> doors = Arrays.asList(
                new Door(false, "1"),
                new Door(true, "2")
        );
        Room room = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(room);
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        smartHome = new SmartHome(rooms, signalisation);
        commandSender = new CommandSenderImpl();
    }

    @Test
    void turnOnAllLightsWhenButtonCodeIsRight() {
        // given
        String buttonCode = "A";
        ButtonCommand buttonCommand = new LightOnAllButtonCommand(
                buttonCode,
                smartHome,
                commandSender,
                false);
        // when
        buttonCommand.execute(buttonCode);
        // then
        assertTrue(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
        assertTrue(lights.get(2).isOn());
    }

    @Test
    void doNothingWhenButtonCodeIsWrong() {
        // given
        String buttonCode = "A";
        ButtonCommand buttonCommand = new LightOnAllButtonCommand(
                buttonCode,
                smartHome,
                commandSender,
                false);
        // when
        String anotherButtonCode = "B";
        buttonCommand.execute(anotherButtonCode);
        // then
        assertTrue(lights.get(0).isOn());
        assertFalse(lights.get(1).isOn());
        assertFalse(lights.get(2).isOn());
    }
}
