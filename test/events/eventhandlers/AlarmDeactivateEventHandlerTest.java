package events.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.AlarmDeactivateEventHandler;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDeactivateEventHandlerTest {

    @Test
    void tryToDeactivateSignalisationSetAlarmWhenItIsActivatedAndEnteredCodeIsInvalid() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl();
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, "1234");
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = "Alarmed";
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsDeactivatedAndEnteredCodeIsInvalid() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl();
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, "1234");
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = "Deactivated";
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsAlarmedAndEnteredCodeIsInvalid() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl();
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, "1234");
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = "Alarmed";
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }

    @Test
    void tryToDeactivateSignalisationSucceedWhenItIsActivatedAndEnteredCodeIsValid() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl();
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, "0000");
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = "Deactivated";
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsDeactivatedAndEnteredCodeIsValid() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl();
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, "0000");
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = "Deactivated";
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }

    @Test
    void tryToDeactivateSignalisationSucceedWhenItIsAlarmedAndEnteredCodeIsValid() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl();
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_DEACTIVATE, "0000");
        EventHandler alarmActivateEventHandler = new AlarmDeactivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = "Deactivated";
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }
}
