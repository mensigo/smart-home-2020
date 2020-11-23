package remotecontrol.buttons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.LightOffAllButtonCommand;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LightOffAllButtonCommandTest {
    private List<Light> lights;
    private SmartHome smartHome;
    private CommandSender commandSender;

    @BeforeEach
    public void prepareSmartHome() {
        String roomName = "kitchen";
        lights= Arrays.asList(
                new Light("1", true),
                new Light("2", true),
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
    void turnOffAllLights() {
        // given
        ButtonCommand buttonCommand = new LightOffAllButtonCommand(
                smartHome,
                commandSender,
                false);
        // when
        buttonCommand.execute();
        // then
        assertFalse(lights.get(0).isOn());
        assertFalse(lights.get(1).isOn());
        assertFalse(lights.get(2).isOn());
    }
}
