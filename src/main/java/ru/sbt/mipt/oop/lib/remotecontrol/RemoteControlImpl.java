package ru.sbt.mipt.oop.lib.remotecontrol;

import com.anothercompany.rc.RemoteControl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;

import java.util.List;

public class RemoteControlImpl implements RemoteControl {
    private final String id;
    private final List<ButtonCommand> buttonsList;

    public RemoteControlImpl(String id, List<ButtonCommand> buttonsList) {
        this.id = id;
        this.buttonsList = buttonsList;
    }

    @Override
    public void onButtonPressed(String enteredButtonCode, String rcId) {
        if (!id.equals(rcId)) return;
        for (ButtonCommand button : buttonsList) {
            button.execute(enteredButtonCode);
        }
    }
}
