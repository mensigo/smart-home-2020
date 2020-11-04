package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.actions.LightOnInHallAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.SmartHome;

public class LightOnInHallButtonCommand implements ButtonCommand {
    private final String buttonCode;
    private final SmartHome smartHome;
    private final CommandSender commandSender;
    private final boolean isQuiet;
    private final String hallName;

    public LightOnInHallButtonCommand(String buttonCode,
                                      SmartHome smartHome,
                                      CommandSender commandSender,
                                      boolean isQuiet,
                                      String hallName) {
        this.buttonCode = buttonCode;
        this.smartHome = smartHome;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
        this.hallName = hallName;
    }

    @Override
    public void execute(String enteredButtonCode) {
        if (buttonCode.equals(enteredButtonCode)) {
            smartHome.execute(new LightOnInHallAction(hallName, isQuiet, commandSender));
        }
    }
}
