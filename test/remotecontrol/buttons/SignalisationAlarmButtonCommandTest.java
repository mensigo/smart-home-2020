package remotecontrol.buttons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.SignalisationAlarmButtonCommand;
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

public class SignalisationAlarmButtonCommandTest {
    private SmartHome smartHome;
    private Signalisation signalisation;

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
        signalisation = new SignalisationImpl("0000");
        smartHome = new SmartHome(rooms, signalisation);
    }

    @Test
    void tryToAlarmSignalisationSucceedWhenItIsActivated() {
        // given
        signalisation.activate("1234");
        ButtonCommand buttonCommand = new SignalisationAlarmButtonCommand(signalisation);
        // when
        buttonCommand.execute();
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAlarmed());
    }

    @Test
    void tryToAlarmSignalisationDoNothingWhenItIsDeactivated() {
        // given
        ButtonCommand buttonCommand = new SignalisationAlarmButtonCommand(signalisation);
        // when
        buttonCommand.execute();
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isDeactivated());
    }

    @Test
    void tryToAlarmSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        signalisation.alarm();
        ButtonCommand buttonCommand = new SignalisationAlarmButtonCommand(signalisation);
        // when
        buttonCommand.execute();
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAlarmed());
    }
}
