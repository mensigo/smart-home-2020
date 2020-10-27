package events.decorators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalisationSensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.events.decorators.SMSSender;
import ru.sbt.mipt.oop.events.decorators.SignalisationEventHandlerRunnerDecorator;
import ru.sbt.mipt.oop.events.decorators.SignalisationSMSSender;
import ru.sbt.mipt.oop.events.eventhandlers.*;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signalisation.SignalStateName;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.signalisation.SignalStateName.*;

public class SignalisationEventHandlerRunnerDecoratorTest {
    private List<Light> lights;
    private List<Door> doors;
    private SmartHome smartHome;
    private SignalisationImpl signalisation;
    private EventHandlerRunner eventHandlerRunner;
    private SMSSender smsSender;

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
        CommandSender commandSender = new CommandSenderImpl();
        eventHandlerRunner = new EventHandlerRunnerImpl(Arrays.asList(
                new AlarmActivateEventHandler(),
                new AlarmDeactivateEventHandler(),
                new LightOnEventHandler(commandSender),
                new LightOffEventHandler(commandSender),
                new DoorOpenEventHandler(commandSender),
                new DoorCloseEventHandler(commandSender),
                new DoorCloseInHallEventHandler("hall", commandSender)
        ));
        smsSender = new SignalisationSMSSender();
    }

    @Test
    void tryToHandleSensorEventSucceedWithObjectAndDoNothingWithSignalisationWhenEventIsSimpleSensorEventAndSignalisationIsDeactivated() {
        // given
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner decoratedEventHandlerRunner = new SignalisationEventHandlerRunnerDecorator(eventHandlerRunner, smsSender);
        // when
        decoratedEventHandlerRunner.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = true;
        SignalStateName expectedSignalisationState = STATE_DEACTIVATED;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectAndSetAlarmSignalisationAndSendSMSWhenEventIsSimpleSensorEventAndSignalisationIsActivated() {
        // given
        signalisation.activate("0000");
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerRunner, smsSender);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        SignalStateName expectedSignalisationState = STATE_ALARMED;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectAndDoNothingWithSignalisationAndSendSMSWhenEventIsSimpleSensorEventAndSignalisationIsAlarmed() {
        // given
        signalisation.activate("0000");
        signalisation.alarm();
        SensorEvent event = new SimpleSensorEvent(LIGHT_ON, "1", false);
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerRunner, smsSender);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        SignalStateName expectedSignalisationState = STATE_ALARMED;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectWhenEventIsSignalisationSensorEventAndSignalisationIsActivated() {
        // given
        signalisation.activate("0000");
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerRunner, smsSender);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        SignalStateName expectedSignalisationState = STATE_ACTIVATED;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectWhenEventIsSignalisationSensorEventAndSignalisationIsDeactivated() {
        // given
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerRunner, smsSender);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        SignalStateName expectedSignalisationState = STATE_ACTIVATED;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }

    @Test
    void tryToHandleSensorEventDoNothingWithObjectWhenEventIsSignalisationSensorEventAndSignalisationIsAlarmed() {
        // given
        signalisation.activate("0000");
        signalisation.alarm();
        SensorEvent event = new SignalisationSensorEvent(ALARM_ACTIVATE, "1234");
        EventHandlerRunner decoratedEventHandlerChooser = new SignalisationEventHandlerRunnerDecorator(eventHandlerRunner, smsSender);
        // when
        decoratedEventHandlerChooser.runHandlers(event, smartHome);
        // then
        boolean expectedObjectState = false;
        SignalStateName expectedSignalisationState = STATE_ALARMED;
        assertEquals(expectedObjectState, lights.get(0).isOn());
        assertEquals(expectedSignalisationState, smartHome.getSignalisation().getState().getName());
    }
}
