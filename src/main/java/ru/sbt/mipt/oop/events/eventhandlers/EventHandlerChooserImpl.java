package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventHandlerChooserImpl implements EventHandlerChooser {
    private final CommandSender commandSender;

    public EventHandlerChooserImpl(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void chooseAndRunHandler(SensorEvent event, SmartHome smartHome) {
        EventHandler eventHandler = null;
        switch (event.getType()) {
            case ALARM_ACTIVATE:
                eventHandler = new AlarmActivateEventHandler();
                break;
            case ALARM_DEACTIVATE:
                eventHandler = new AlarmDeactivateEventHandler();
                break;
            case LIGHT_ON:
                eventHandler = new LightOnEventHandler(commandSender);
                break;
            case LIGHT_OFF:
                eventHandler = new LightOffEventHandler(commandSender);
                break;
            case DOOR_OPEN:
                eventHandler = new DoorOpenEventHandler(commandSender);
                break;
            case DOOR_CLOSE:
                eventHandler = new DoorCloseEventHandler(commandSender);
                break;
        }
        eventHandler.handleEvent(event, smartHome);
    }
}
