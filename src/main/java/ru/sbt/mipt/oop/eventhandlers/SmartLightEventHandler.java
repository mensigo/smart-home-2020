package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.actions.TurnOffLightAction;
import ru.sbt.mipt.oop.actions.TurnOnLightAction;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.LightActionable;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class SmartLightEventHandler implements SmartObjectEventHandler {

    @Override
    public void handleEvent(SensorEvent event, Object object) {
        if (!(object instanceof LightActionable)) {
            throw new RuntimeException("SmartLightEventHandler::handleEvent(..) param @object is not instanceof LightActionable.");
        }
        LightActionable light = ((LightActionable) object);
        if (event.getType() == LIGHT_ON) {
            light.execute(new TurnOnLightAction());
            if (!event.getQueit()) {
                System.out.println("Light " + light.getId() + " in place " + light.getPlaceName() + " was turned on.");
            }
        } else {
            light.execute(new TurnOffLightAction());
            if (!event.getQueit()) {
                System.out.println("Light " + light.getId() + " in place " + light.getPlaceName() + " was turned off.");
            }
        }
    }
}
