package ru.sbt.mipt.oop.lib.remotecontrol.buttons;

import ru.sbt.mipt.oop.signalisation.Signalisation;

public class SignalisationAlarmButtonCommand implements ButtonCommand {
    private final Signalisation signalisation;

    public SignalisationAlarmButtonCommand(Signalisation signalisation) {
        this.signalisation = signalisation;
    }

    @Override
    public void execute() {
        signalisation.alarm();
    }
}
