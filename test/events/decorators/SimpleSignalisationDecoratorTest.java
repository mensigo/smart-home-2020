package events.decorators;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventChooserDecorator;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunnerImpl;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class SimpleSignalisationDecoratorTest {

    @Test
    void tryToHandleSensorEventSucceedWithObjectAndDoNothingWithSignalisationWhenEventIsSimpleSensorEventAndSignalisationIsDeactivated() {
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
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventChooserDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = true;
        String expectedSignalisationState = DeactivatedSignalState.stateName;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState());
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
        SignalisationImpl signalisation = new SignalisationImpl("0000", ActivatedSignalState.stateName);
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventChooserDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = AlarmedSignalState.stateName;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState());
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
        SignalisationImpl signalisation = new SignalisationImpl("0000", AlarmedSignalState.stateName);
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventChooserDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = AlarmedSignalState.stateName;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState());
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
        SignalisationImpl signalisation = new SignalisationImpl("0000", ActivatedSignalState.stateName);
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventChooserDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = ActivatedSignalState.stateName;
        assertEquals(expectedObjectState, lights.get(0).isOn());
//        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState());
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
        SignalisationImpl signalisation = new SignalisationImpl("0000", DeactivatedSignalState.stateName);
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventChooserDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = ActivatedSignalState.stateName;
        assertEquals(expectedObjectState, lights.get(0).isOn());
//        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState());
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
        SignalisationImpl signalisation = new SignalisationImpl("0000", AlarmedSignalState.stateName);
        SmartHome smartHome = new SmartHome(rooms, signalisation);
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner eventHandlerChooser = new EventHandlerRunnerImpl(new CommandSenderImpl());
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventChooserDecorator(eventHandlerChooser);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        String expectedSignalisationState = AlarmedSignalState.stateName;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState());
    }
}
