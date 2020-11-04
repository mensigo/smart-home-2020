package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.SmartHome;

public class LightOffAllButtonCommand implements ButtonCommand {
    private final String buttonCode;
    private final SmartHome smartHome;
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public LightOffAllButtonCommand(String buttonCode,
                                    SmartHome smartHome,
                                    CommandSender commandSender,
                                    boolean isQuiet) {
        this.buttonCode = buttonCode;
        this.smartHome = smartHome;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void execute(String enteredButtonCode) {
        if (buttonCode.equals(enteredButtonCode)) {
            smartHome.execute(new LightOffAction(isQuiet, commandSender));
        }
    }
}
