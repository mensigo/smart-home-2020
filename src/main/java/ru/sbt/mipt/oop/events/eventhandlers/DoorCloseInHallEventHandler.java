package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.actions.DoorCloseByIdInHallEffectAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSE;

public class DoorCloseInHallEventHandler implements EventHandler {
    private final CommandSender commandSender;
    private final String hallName;

    public DoorCloseInHallEventHandler(String hallName, CommandSender commandSender) {
        this.commandSender = commandSender;
        this.hallName = hallName;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event instanceof SimpleSensorEvent && event.getType().equals(DOOR_CLOSE)) {
            smartHome.execute(new DoorCloseByIdInHallEffectAction(event.getObjectId(), hallName, event.isQuiet(), commandSender));
        }
    }
}
