package remotecontrol.buttons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.SignalisationActivateButtonCommand;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.Signalisation;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignalisationActivateButtonCommandTest {
    private SmartHome smartHome;
    private Signalisation signalisation;
    private String standardCode;

    @BeforeEach
    public void prepareSmartHome() {
        String roomName = "kitchen";
        List<Light> lights = Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room room = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(room);
        standardCode = "0000";
        signalisation = new SignalisationImpl(standardCode);
        smartHome = new SmartHome(rooms, signalisation);
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsActivated() {
        // given
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        ButtonCommand buttonCommand = new SignalisationActivateButtonCommand(enteredCode, signalisation);
        // when
        buttonCommand.execute();
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isActivated());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(enteredCode));
    }

    @Test
    void tryToActivateSignalisationSucceedWhenItIsDeactivated() {
        // given
        String enteredCode = "1234";
        ButtonCommand buttonCommand = new SignalisationActivateButtonCommand(enteredCode, signalisation);
        // when
        buttonCommand.execute();
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isActivated());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(enteredCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        signalisation.alarm();
        ButtonCommand buttonCommand = new SignalisationActivateButtonCommand(enteredCode, signalisation);
        // when
        buttonCommand.execute();
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAlarmed());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(enteredCode));
    }
}
