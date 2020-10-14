package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.actions.DoorCloseAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public class DoorCloseEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public DoorCloseEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        smartHome.execute(new DoorCloseAction(event.getObjectId(), event.isQuiet(), commandSender));
    }
}
