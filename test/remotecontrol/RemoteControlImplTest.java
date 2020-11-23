package remotecontrol;

import com.anothercompany.rc.RemoteControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.lib.remotecontrol.RemoteControlImpl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.*;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RemoteControlImplTest {
    private List<Light> lights;
    private List<Door> doors;
    private SmartHome smartHome;
    private String standardSignalCode;
    private Map<String, ButtonCommand> buttons;
    private List<String> allowedButtonCodes;

    @BeforeEach
    public void prepareSmartHome() {
        String roomName = "hall";
        lights = Arrays.asList(
                new Light("1", false),
                new Light("2", true),
                new Light("3", true)
        );
        doors = Collections.singletonList(
                new Door(true, "1")
        );
        Room room = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(room);
        standardSignalCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(standardSignalCode);
        smartHome = new SmartHome(rooms, signalisation);
        CommandSender commandSender = new CommandSenderImpl();
        String hallName = "hallName", enteredSignalCode = "0000";
        buttons = Map.of("A", new LightOffAllButtonCommand(smartHome, commandSender, false));
        allowedButtonCodes = Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4");
    }

    @Test
    void tryToHandleButtonCommandDoNothingWhenButtonCodeIsNotAllowed() {
        // given
        String rcId = "abc123";
        RemoteControl remoteControl = new RemoteControlImpl(rcId, buttons, allowedButtonCodes);
        // when
        String enteredButtonCode = "Z";
        remoteControl.onButtonPressed(enteredButtonCode, rcId);
        // then
        assertFalse(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
        assertTrue(doors.get(0).isOpen());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isDeactivated());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(standardSignalCode));
    }

    @Test
    void tryToHandleButtonCommandDoNothingWhenButtonCodeIsAllowedButNotImplementedByAnyButton() {
        // given
        String rcId = "abc123";
        RemoteControl remoteControl = new RemoteControlImpl(rcId, buttons, allowedButtonCodes);
        // when
        String enteredButtonCode = "B";
        remoteControl.onButtonPressed(enteredButtonCode, rcId);
        // then
        assertFalse(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
        assertTrue(doors.get(0).isOpen());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isDeactivated());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(standardSignalCode));
    }

    @Test
    void tryToHandleButtonCommandSucceedWhenButtonCodeIsAllowedAndImplementedBySomeButton() {
        // given
        String rcId = "abc123";
        RemoteControl remoteControl = new RemoteControlImpl(rcId, buttons, allowedButtonCodes);
        // when
        String enteredButtonCode = "A";
        remoteControl.onButtonPressed(enteredButtonCode, rcId);
        // then
        assertFalse(lights.get(0).isOn());
        assertFalse(lights.get(1).isOn());
        assertFalse(lights.get(2).isOn());
    }
}
