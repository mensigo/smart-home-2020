package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;

//import static ru.sbt.mipt.oop.general.Application.sendCommand;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.general.Application.sendCommand;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void handleDoorClosedInHallEvent() {
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        for (Room homeRoom : rooms) {
            for (Light light : homeRoom.getLights()) {
                SensorEvent turnOff = new SensorEvent(LIGHT_OFF, light.getId());
                light.handleEventQuiet(turnOff);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }

    }
}
