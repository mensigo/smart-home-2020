package ru.sbt.mipt.oop.events.eventhandlers;

<<<<<<< HEAD
import ru.sbt.mipt.oop.actions.LightOnAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

=======
import ru.sbt.mipt.oop.actions.LightOnByIdAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

>>>>>>> hometask-2
public class LightOnEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public LightOnEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
<<<<<<< HEAD
        smartHome.execute(new LightOnAction(event.getObjectId(), event.isQuiet(), commandSender));
=======
        if (event instanceof SimpleSensorEvent && event.getType().equals(LIGHT_ON)) {
            smartHome.execute(new LightOnByIdAction(event.getObjectId(), event.isQuiet(), commandSender));
        }
>>>>>>> hometask-2
    }
}
