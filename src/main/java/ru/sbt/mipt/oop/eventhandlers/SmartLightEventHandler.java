package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.Light;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class SmartLightEventHandler implements SmartObjectEventHandler {

    @Override
    public void handleEvent(SensorEvent event, Object object) {
        if (!(object instanceof Light)) {
            throw new RuntimeException("SmartLightEventHandler::handleEvent(..) param @object is not instanceof Light.");
        }
        Light light = ((Light) object);
        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            if (!event.getQueit()) {
                System.out.println("Light " + light.getId() + " in place " + light.getPlaceName() + " was turned on.");
            }
        } else {
            light.setOn(false);
            if (!event.getQueit()) {
                System.out.println("Light " + light.getId() + " in place " + light.getPlaceName() + " was turned off.");
            }
        }
    }
}
