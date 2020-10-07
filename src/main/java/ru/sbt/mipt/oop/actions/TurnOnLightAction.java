package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.objects.LightActionable;

public class TurnOnLightAction implements Action {

    @Override
    public void executeAction(Object object) {
        if (!(object instanceof LightActionable)) {
            return;
        }
        ((LightActionable) object).setOn(true);
    }
}
