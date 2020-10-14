package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.actions.LightOnAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public class LightOnEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public LightOnEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        smartHome.execute(new LightOnAction(event.getObjectId(), event.isQuiet(), commandSender));
    }
}
