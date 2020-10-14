package events.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.AlarmActivateEventHandler;
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
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_ACTIVATE;

public class AlarmActivateEventHandlerTest {

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsActivated() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl("0000", ActivatedSignalState.stateName);
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandler alarmActivateEventHandler = new AlarmActivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = ActivatedSignalState.stateName;
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }

    @Test
    void tryToActivateSignalisationSucceedWhenItIsDeactivated() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl("0000", DeactivatedSignalState.stateName);
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandler alarmActivateEventHandler = new AlarmActivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = ActivatedSignalState.stateName;
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String roomName = "kitchen";
        List<Light> lights= Arrays.asList(
                new Light("1", false),
                new Light("2", true));
        List<Door> doors = Collections.singletonList(
                new Door(false, "1"));
        Room bathroom = new Room(lights, doors, roomName);
        List<Room> rooms = Collections.singletonList(bathroom);
        SignalisationImpl signalisation = new SignalisationImpl("0000", AlarmedSignalState.stateName);
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandler alarmActivateEventHandler = new AlarmActivateEventHandler();
        // when
        alarmActivateEventHandler.handleEvent(event, smartHome);
        // then
        String expectedState = AlarmedSignalState.stateName;
        assertEquals(expectedState, smartHome.getSignalisation().getState());
    }
}
