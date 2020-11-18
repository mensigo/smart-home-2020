package events.eventhandlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.AlarmActivateEventHandler;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_ACTIVATE;

public class AlarmActivateEventHandlerTest {
    private List<Light> lights;
    private List<Door> doors;
    private SmartHome smartHome;
    private SignalisationImpl signalisation;

    @BeforeEach
    public void prepareSmartHome() {
        String roomName = "kitchen";
        lights = Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        doors = Collections.singletonList(
                new Door(false, "1"));
        Room room = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(room);
        signalisation = new SignalisationImpl("0000");
        smartHome = new SmartHome(rooms, signalisation);
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsActivated() {
        // given
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, enteredCode);
        EventHandler alarmActivateEventHandler = new AlarmActivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isActivated());
    }

    @Test
    void tryToActivateSignalisationSucceedWhenItIsDeactivated() {
        // given
        String enteredCode = "1234";
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, enteredCode);
        EventHandler alarmActivateEventHandler = new AlarmActivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isActivated());
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        signalisation.alarm();
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandler alarmActivateEventHandler = new AlarmActivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAlarmed());
    }
}
