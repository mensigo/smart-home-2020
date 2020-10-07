package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.actions.CloseDoorInHallAction;
import ru.sbt.mipt.oop.commandactions.TurnOffLightCommandAction;
import ru.sbt.mipt.oop.actions.TurnOffLightAction;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHomeActionable;

public class CommandSenderImpl implements CommandSender {

    @Override
    public void handleSpecialEvent(SensorEvent event, Object object) {
        if (!(object instanceof SmartHomeActionable)) {
            throw new RuntimeException("CommandSenderImpl::handleSpecialEvent(..) param @object is not instanceof SmartHomeActionable.");
        }
        SmartHomeActionable smartHome = (SmartHomeActionable) object;
        if (event.isDoorClosedInHallSpecialEvent(smartHome)) {
            // if we get event to close the door in the hall - it means, its the front door to be closed.
            // in that case we want to turn off the light in the whole house automatically (dat house aint dummy!)
            // turn off lights
            smartHome.execute(new TurnOffLightAction());
            // send commands
            smartHome.execute(new TurnOffLightCommandAction());
            // close the door
            smartHome.execute(new CloseDoorInHallAction());
        }
        // add special events' handlers here...
    }
}
