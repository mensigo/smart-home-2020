package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public class LightOffEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public LightOffEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        smartHome.execute(new LightOffAction(event.getObjectId(), event.isQuiet(), commandSender));
    }
}
