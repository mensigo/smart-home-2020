package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.signalisation.Signalisation;

public class SignalisationActivateButtonCommand implements ButtonCommand {
    private final String buttonCode;
    private final String enteredSignalCode;
    private final Signalisation signalisation;

    public SignalisationActivateButtonCommand(String buttonCode,
                                              String enteredSignalCode,
                                              Signalisation signalisation) {
        this.buttonCode = buttonCode;
        this.enteredSignalCode = enteredSignalCode;
        this.signalisation = signalisation;
    }

    @Override
    public void execute(String enteredButtonCode) {
        if (buttonCode.equals(enteredButtonCode)) {
            signalisation.activate(enteredSignalCode);
        }
    }
}
