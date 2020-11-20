package remotecontrol.buttons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.DoorCloseInHallButtonCommand;
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

public class DoorCloseInHallButtonCommandTest {
    private List<Light> lights;
    private List<Door> doors;
    private SmartHome smartHome;
    private CommandSender commandSender;

    @BeforeEach
    public void prepareSmartHome() {
        String roomName = "hall";
        lights = Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        doors = Collections.singletonList(
                new Door(true, "1")
        );
        Room room = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(room);
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        smartHome = new SmartHome(rooms, signalisation);
        commandSender = new CommandSenderImpl();
    }

    @Test
    void handleDoorInHallScenarioCloseDoorTurnOffAllLightsAndSendCommandsToThem() {
        // given
        ButtonCommand buttonCommand = new DoorCloseInHallButtonCommand(
                smartHome,
                commandSender,
                false,
                "hall"
        );
        // when
        buttonCommand.execute();
        // then
        assertFalse(doors.get(0).isOpen());
        assertFalse(lights.get(0).isOn());
        assertFalse(lights.get(1).isOn());
    }
}
