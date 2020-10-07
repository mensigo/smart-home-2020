package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHomeActionable;

public class EventScenarioChooserImpl implements EventScenarioChooser {

    @Override
    public void chooseAndImplementScenario(SensorEvent event,
                                           SmartHomeActionable smartHome,
                                           CommandSender commandSender) {
        if (event.isSendCommandNeedyEvent(smartHome)) {
            // "special" events: DoorClosedInHall etc.
            commandSender.handleSpecialEvent(event, smartHome);
        }
        else {
            // "usual" events: LightActionable, DoorActionable events etc.
            smartHome.handleUsualEvent(event);
        }
    }
}