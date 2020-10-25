package events.decorators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunnerImpl;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class SignalisationEventHandlerRunnerDecoratorTest {
    private List<Light> lights = null;
    private List<Door> doors = null;
    private SmartHome smartHome = null;

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
        SignalisationImpl signalisation = new SignalisationImpl();
        smartHome = new SmartHome(rooms, signalisation);
    }

    @Test
    void tryToHandleSensorEventSucceedWithObjectAndDoNothingWithSignalisationWhenEventIsSimpleSensorEventAndSignalisationIsDeactivated() {
        // given
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerRunner = new SignalisationEventHandlerRunnerDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerRunner.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = true;
        String expectedSignalisationState = "Deactivated";
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectAndSetAlarmSignalisationAndSendSMSWhenEventIsSimpleSensorEventAndSignalisationIsActivated() {
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
        signalisation.activate("0000");
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = "Alarmed";
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectAndDoNothingWithSignalisationAndSendSMSWhenEventIsSimpleSensorEventAndSignalisationIsAlarmed() {
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
        signalisation.activate("0000");
        signalisation.alarm();
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = "Alarmed";
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectWhenEventIsSignalisationSensorEventAndSignalisationIsActivated() {
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
        signalisation.activate("0000");
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = "Activated";
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectWhenEventIsSignalisationSensorEventAndSignalisationIsDeactivated() {
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
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = "Activated";
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectWhenEventIsSignalisationSensorEventAndSignalisationIsAlarmed() {
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
        signalisation.activate("0000");
        signalisation.alarm();
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = "Alarmed";
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }
}
