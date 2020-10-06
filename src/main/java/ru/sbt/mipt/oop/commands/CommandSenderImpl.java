package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;

public class CommandSenderImpl implements CommandSender {

    @Override
    public void handleSpecialEvent(SensorEvent event, Object object) {
        if (!(object instanceof SmartHome)) {
            throw new RuntimeException("CommandSenderImpl::handleSpecialEvent(..) param @object is not instanceof SmartHome.");
        }
        SmartHome smartHome = (SmartHome) object;
        if (event.isDoorClosedInHallSpecialEvent(smartHome)) {
            // if we get event to close the door in the hall - it means, its the front door to be closed.
            // in that case we want to turn off the light in the whole house automatically (dat house aint dummy!)
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    SensorEvent lightOffEvent = new SensorEvent(LIGHT_OFF, light.getId(), true);
                    light.handleUsualEvent(lightOffEvent);
                    SensorCommand command = new SensorCommand(SensorCommandType.LIGHT_OFF, light.getId());
                    sendCommand(command);
                }
            }
        }
        // add special events' handlers here...
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
