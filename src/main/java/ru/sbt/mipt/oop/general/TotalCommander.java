package ru.sbt.mipt.oop.general;

import ru.sbt.mipt.oop.commands.SensorCommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.general.Application.sendCommand;

public class TotalCommander {
    public void handleSpecialEvent(SensorEvent event, SmartHome smartHome) {
        if (event.isDoorClosedInHallSpecialEvent()) {
            // if we get event to close the door in the hall - it means, its the front door to be closed.
            // in that case we want to turn off the light in the whole house automatically (dat house aint dummy!)
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    SensorEvent turnOff = new SensorEvent(LIGHT_OFF, light.getId());
                    light.handleUsualEventQuietly(turnOff);
                    SensorCommand command = new SensorCommand(SensorCommandType.LIGHT_OFF, light.getId());
                    sendCommand(command);
                }
            }
        }
        // add special events' handlers here...
    }
}
