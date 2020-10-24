package ru.sbt.mipt.oop.events.eventhandlers;

<<<<<<< HEAD
import ru.sbt.mipt.oop.actions.DoorCloseAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

=======
import ru.sbt.mipt.oop.actions.DoorCloseByIdAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SimpleSensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSE;

>>>>>>> hometask-2
public class DoorCloseEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public DoorCloseEventHandler(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
<<<<<<< HEAD
        smartHome.execute(new DoorCloseAction(event.getObjectId(), event.isQuiet(), commandSender));
=======
        if (event instanceof SimpleSensorEvent && event.getType().equals(DOOR_CLOSE)) {
            smartHome.execute(new DoorCloseByIdAction(event.getObjectId(), event.isQuiet(), commandSender));
        }
>>>>>>> hometask-2
    }
}
