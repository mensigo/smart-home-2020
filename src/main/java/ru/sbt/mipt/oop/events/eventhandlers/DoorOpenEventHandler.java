package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.actions.DoorOpenAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public class DoorOpenEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public DoorOpenEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        smartHome.execute(new DoorOpenAction(event.getObjectId(), event.isQuiet(), commandSender));
    }
}
