package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;

public class LightOffAction implements Action {
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public LightOffAction(boolean isQuiet, CommandSender commandSender) {
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void act(Object object) {
        if (!(object instanceof Light)) {
            return;
        }
        Light light = (Light) object;
        String placeName = (light.getPlace() instanceof Room) ? ((Room) light.getPlace()).getName() : null;
        if (light.isOn()) {
            light.setOn(false);
            if (!isQuiet) {
                System.out.println("Light " + light.getId() + " in place " + placeName + " has been turned off.");
            }
        } else {
            if (!isQuiet) {
                System.out.println("Light " + light.getId() + " in place " + placeName + " has been already turned off.");
            }
        }
    }
}