package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.actions.DoorCloseInHallAction;
import ru.sbt.mipt.oop.actions.DoorCloseInHallEffectAction;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.objects.SmartHome;

public class DoorCloseInHallButtonCommand implements ButtonCommand {
    private final String buttonCode;
    private final SmartHome smartHome;
    private final CommandSender commandSender;
    private final boolean isQuiet;
    private final String hallName;

    public DoorCloseInHallButtonCommand(String buttonCode,
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
            smartHome.execute(new DoorCloseInHallAction(hallName, isQuiet, commandSender));
            smartHome.execute(new DoorCloseInHallEffectAction(hallName, isQuiet, commandSender));
        }
    }
}
