package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventScenarioChooserImpl implements EventScenarioChooser {

    @Override
    public void chooseAndImplementScenario(SensorEvent event,
                                           SmartHome smartHome,
                                           CommandSender commandSender) {
        if (event.isSendCommandNeedyEvent(smartHome)) {
            // "special" events: DoorClosedInHall etc.
            commandSender.handleSpecialEvent(event, smartHome);
        }
        else {
            // "usual" events: Light, Door events etc.
            smartHome.handleUsualEvent(event);
        }
    }
}