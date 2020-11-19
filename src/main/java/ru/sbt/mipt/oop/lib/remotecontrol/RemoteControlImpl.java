package ru.sbt.mipt.oop.lib.remotecontrol;

import com.anothercompany.rc.RemoteControl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;

import java.util.List;

public class RemoteControlImpl implements RemoteControl {
    private final String id;
    private final List<ButtonCommand> buttonsList;
    private final List<String> allowedButtonCodes;

    public RemoteControlImpl(String id, List<ButtonCommand> buttonsList, List<String> allowedButtonCodes) {
        this.id = id;
        this.buttonsList = buttonsList;
        this.allowedButtonCodes = allowedButtonCodes;
    }

    @Override
    public void onButtonPressed(String enteredButtonCode, String rcId) {
        if (!id.equals(rcId) || !isButtonCodeAllowed(enteredButtonCode)) return;
        for (ButtonCommand button : buttonsList) {
            button.execute(enteredButtonCode);
        }
    }

    private boolean isButtonCodeAllowed(String buttonCode) {
        boolean isAllowed = false;
        for (String allowedButtonCode : allowedButtonCodes) {
            if (allowedButtonCode.equals(buttonCode)) {
                isAllowed = true;
                break;
            }
        }
        return isAllowed;
    }
}
