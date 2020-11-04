package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;

public class LightOnAction implements Action {
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public LightOnAction(boolean isQuiet, CommandSender commandSender) {
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
        if (!light.isOn()) {
            light.setOn(true);
            if (!isQuiet) {
                System.out.println("Light " + light.getId() + " in place " + placeName + " has been turned on.");
            }
        } else {
            if (!isQuiet) {
                System.out.println("Light " + light.getId() + " in place " + placeName + " has been already turned on.");
            }
        }
    }
}
