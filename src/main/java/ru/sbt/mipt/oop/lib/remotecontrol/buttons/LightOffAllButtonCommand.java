package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.SmartHome;

public class LightOffAllButtonCommand implements ButtonCommand {
    private final SmartHome smartHome;
    private final CommandSender commandSender;
    private final boolean isQuiet;

    public LightOffAllButtonCommand(SmartHome smartHome,
                                    CommandSender commandSender,
                                    boolean isQuiet) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
        this.isQuiet = isQuiet;
    }

    @Override
    public void execute() {
        smartHome.execute(new LightOffAction(isQuiet, commandSender));
    }
}
