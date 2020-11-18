package events.eventhandlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.AlarmDeactivateEventHandler;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
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
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDeactivateEventHandlerTest {
    private List<Light> lights;
    private List<Door> doors;
    private SmartHome smartHome;
    private Signalisation signalisation;

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
    void tryToDeactivateSignalisationSetAlarmWhenItIsActivatedAndEnteredCodeIsInvalid() {
        // given
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, enteredCode);
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAlarmed());
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsDeactivatedAndEnteredCodeIsInvalid() {
        // given
        String enteredCode = "1234";
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, enteredCode);
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isDeactivated());
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsAlarmedAndEnteredCodeIsInvalid() {
        // given
        signalisation.activate("0000");
        signalisation.alarm();
        String enteredCode = "1234";
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, enteredCode);
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isAlarmed());
    }

    @Test
    void tryToDeactivateSignalisationSucceedWhenItIsActivatedAndEnteredCodeIsValid() {
        // given
        String enteredCode = "0000";
        signalisation.activate(enteredCode);
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, enteredCode);
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isDeactivated());
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsDeactivatedAndEnteredCodeIsValid() {
        // given
        String enteredCode = "0000";
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, enteredCode);
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isDeactivated());
    }

    @Test
    void tryToDeactivateSignalisationSucceedWhenItIsAlarmedAndEnteredCodeIsValid() {
        // given
        String enteredCode = "0000";
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, enteredCode);
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        assertTrue(((SignalisationImpl) smartHome.getSignalisation()).isDeactivated());
    }
}
