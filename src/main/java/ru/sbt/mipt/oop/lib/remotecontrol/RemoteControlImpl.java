package ru.sbt.mipt.oop.lib.remotecontrol;

import com.anothercompany.rc.RemoteControl;
import ru.sbt.mipt.oop.lib.remotecontrol.buttons.ButtonCommand;

import java.util.List;
import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
    private final String id;
    private final Map<String, ButtonCommand> buttons;
    private final List<String> allowedButtonCodes;

    public RemoteControlImpl(String id, Map<String, ButtonCommand> buttons, List<String> allowedButtonCodes) {
        this.id = id;
        this.buttons = buttons;
        this.allowedButtonCodes = allowedButtonCodes;
    }

    @Override
    public void onButtonPressed(String enteredButtonCode, String rcId) {
        if (!id.equals(rcId) || !isButtonCodeAllowed(enteredButtonCode)) return;
        for (String buttonCode : buttons.keySet()) {
            if (buttonCode.equals(enteredButtonCode)) {
                buttons.get(buttonCode).execute();
                break;
            }
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
