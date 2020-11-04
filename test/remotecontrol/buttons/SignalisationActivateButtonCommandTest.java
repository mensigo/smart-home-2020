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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.signalisation.SignalStateName.*;

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
    void tryToActivateSignalisationDoNothingWhenItIsActivatedAndButtonCodeIsRight() {
        // given
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        String buttonCode = "A";
        ButtonCommand buttonCommand = new SignalisationActivateButtonCommand(
                buttonCode,
                enteredCode,
                signalisation
        );
        // when
        buttonCommand.execute(buttonCode);
        // then
        assertEquals(STATE_ACTIVATED, smartHome.getSignalisation().getState().getName());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(enteredCode));
    }

    @Test
    void tryToActivateSignalisationSucceedWhenItIsDeactivatedAndButtonCodeIsRight() {
        // given
        String enteredCode = "1234";
        String buttonCode = "A";
        ButtonCommand buttonCommand = new SignalisationActivateButtonCommand(
                buttonCode,
                enteredCode,
                signalisation
        );
        // when
        buttonCommand.execute(buttonCode);
        // then
        assertEquals(STATE_ACTIVATED, smartHome.getSignalisation().getState().getName());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(enteredCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsAlarmedAndButtonCodeIsRight() {
        // given
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        signalisation.alarm();
        String buttonCode = "A";
        ButtonCommand buttonCommand = new SignalisationActivateButtonCommand(
                buttonCode,
                enteredCode,
                signalisation
        );
        // when
        buttonCommand.execute(buttonCode);
        // then
        assertEquals(STATE_ALARMED, smartHome.getSignalisation().getState().getName());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(enteredCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenButtonCodeIsWrong() {
        // given
        String enteredCode = "1234";
        String buttonCode = "A";
        ButtonCommand buttonCommand = new SignalisationActivateButtonCommand(
                buttonCode,
                enteredCode,
                signalisation
        );
        // when
        String anotherButtonCode = "B";
        buttonCommand.execute(anotherButtonCode);
        // then
        assertEquals(STATE_DEACTIVATED, smartHome.getSignalisation().getState().getName());
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAccessCode(standardCode));
    }
}
