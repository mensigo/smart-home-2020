package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.actions.LightOnInHallAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.SmartHome;

public class LightOnInHallButtonCommand implements ButtonCommand {
    private final SmartHome smartHome;
    private final CommandSender commandSender;
    private final boolean isQuiet;
    private final String hallName;

    public LightOnInHallButtonCommand(SmartHome smartHome,
                                      CommandSender commandSender,
                                      boolean isQuiet,
                                      String hallName) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
        this.hallName = hallName;
    }

    @Override
    public void execute() {
        smartHome.execute(new LightOnInHallAction(hallName, isQuiet, commandSender));
    }
}
