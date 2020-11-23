package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.signalisation.Signalisation;

public class SignalisationActivateButtonCommand implements ButtonCommand {
    private final String enteredSignalCode;
    private final Signalisation signalisation;

    public SignalisationActivateButtonCommand(String enteredSignalCode,
                                              Signalisation signalisation) {
        this.enteredSignalCode = enteredSignalCode;
        this.signalisation = signalisation;
    }

    @Override
    public void execute() {
        signalisation.activate(enteredSignalCode);
    }
}
