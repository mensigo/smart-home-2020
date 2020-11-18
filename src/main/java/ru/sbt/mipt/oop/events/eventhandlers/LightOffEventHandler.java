package ru.sbt.mipt.oop.events.eventhandlers;

import ru.sbt.mipt.oop.actions.LightOffByIdAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;

public class LightOffEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public LightOffEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event instanceof SimpleSensorEvent && event.getType().equals(LIGHT_OFF)) {
            smartHome.execute(new LightOffByIdAction(event.getObjectId(), event.isQuiet(), commandSender));
        }
    }
}
