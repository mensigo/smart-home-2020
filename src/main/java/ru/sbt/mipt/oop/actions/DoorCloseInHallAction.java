package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
<<<<<<< HEAD
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
=======
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;
>>>>>>> hometask-2

import static ru.sbt.mipt.oop.commands.SensorCommandType.LIGHT_OFF;

public class DoorCloseInHallAction implements Action {
    private final String objectId;
    private final CommandSender commandSender;
    private final boolean isQuiet;
<<<<<<< HEAD
=======
    private SmartHome smartHome;
>>>>>>> hometask-2

    public DoorCloseInHallAction(String objectId, boolean isQuiet, CommandSender commandSender) {
        this.objectId = objectId;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void act(Object object) {
<<<<<<< HEAD
        if (!(object instanceof Door || object instanceof Light)) {
=======
        if (object instanceof SmartHome) {
            smartHome = (SmartHome) object;
>>>>>>> hometask-2
            return;
        }
        if (object instanceof Door) {
            Door door = ((Door) object);
<<<<<<< HEAD
            if (door.getId().equals(objectId) && door.isOpen()) {
                door.setOpen(false);
                if (!isQuiet) {
                    System.out.println("Door " + door.getId() + " in place " + door.getPlaceName() + " has been closed.");
                }
            }
        } else {
            Light light = ((Light) object);
            if (light.isOn()) {
                light.setOn(false);
            }
            SensorCommand sensorCommand = new SensorCommand(LIGHT_OFF, light.getId());
            commandSender.sendCommand(sensorCommand);
=======
            if (door.getId().equals(objectId) && door.isOpen()
                    && door.getPlaceName().equalsIgnoreCase("hall")) {
                smartHome.execute(new LightOffAction(isQuiet, commandSender));
                smartHome.execute(new LightSendCmdAction(LIGHT_OFF, commandSender));
            }
>>>>>>> hometask-2
        }
    }
}
