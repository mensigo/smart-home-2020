package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.signalisation.Signalisation;

public class SignalisationAlarmButtonCommand implements ButtonCommand {
    private final String buttonCode;
    private final Signalisation signalisation;

    public SignalisationAlarmButtonCommand(String buttonCode,
                                           Signalisation signalisation) {
        this.buttonCode = buttonCode;
        this.signalisation = signalisation;
    }

    @Override
    public void execute(String enteredButtonCode) {
        if (buttonCode.equals(enteredButtonCode)) {
            signalisation.alarm();
        }
    }
}
