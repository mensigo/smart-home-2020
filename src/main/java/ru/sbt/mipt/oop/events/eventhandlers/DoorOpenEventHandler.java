package ru.sbt.mipt.oop.events.eventhandlers;

<<<<<<< HEAD
import ru.sbt.mipt.oop.actions.DoorOpenAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

=======
import ru.sbt.mipt.oop.actions.DoorOpenByIdAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

>>>>>>> hometask-2
public class DoorOpenEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public DoorOpenEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
<<<<<<< HEAD
        smartHome.execute(new DoorOpenAction(event.getObjectId(), event.isQuiet(), commandSender));
=======
        if (event instanceof SimpleSensorEvent && event.getType().equals(DOOR_OPEN)) {
            smartHome.execute(new DoorOpenByIdAction(event.getObjectId(), event.isQuiet(), commandSender));
        }
>>>>>>> hometask-2
    }
}
