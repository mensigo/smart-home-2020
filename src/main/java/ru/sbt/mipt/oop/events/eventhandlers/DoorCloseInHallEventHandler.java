package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.actions.DoorCloseByIdAction;
import ru.sbt.mipt.oop.actions.DoorCloseInHallAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSE;

public class DoorCloseInHallEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public DoorCloseInHallEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event instanceof SimpleSensorEvent & event.getType().equals(DOOR_CLOSE)) {
            smartHome.execute(new DoorCloseByIdAction(event.getObjectId(), event.isQuiet(), commandSender));
            smartHome.execute(new DoorCloseInHallAction(event.getObjectId(), event.isQuiet(), commandSender));
        }
    }
}
