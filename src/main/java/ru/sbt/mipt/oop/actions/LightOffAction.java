package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Light;

public class LightOffAction implements Action {
<<<<<<< HEAD
    private final String objectId;
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public LightOffAction(String objectId, boolean isQuiet, CommandSender commandSender) {
        this.objectId = objectId;
=======
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public LightOffAction(boolean isQuiet, CommandSender commandSender) {
>>>>>>> hometask-2
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void act(Object object) {
        if (!(object instanceof Light)) {
            return;
        }
        Light light = (Light) object;
<<<<<<< HEAD
        if (light.getId().equals(objectId)) {
            if (light.isOn()) {
                light.setOn(false);
                if (!isQuiet) {
                    System.out.println("Light " + light.getId() + " in place " + light.getPlaceName() + " has been turned off.");
                }
            } else {
                if (!isQuiet) {
                    System.out.println("Light " + light.getId() + " in place " + light.getPlaceName() + " has been already turned off.");
                }
=======
        if (light.isOn()) {
            light.setOn(false);
            if (!isQuiet) {
                System.out.println("Light " + light.getId() + " in place " + light.getPlaceName() + " has been turned off.");
            }
        } else {
            if (!isQuiet) {
                System.out.println("Light " + light.getId() + " in place " + light.getPlaceName() + " has been already turned off.");
>>>>>>> hometask-2
            }
        }
    }
}
