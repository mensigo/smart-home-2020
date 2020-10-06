package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;

public interface EventScenarioChooser {
    void chooseAndImplementScenario(SensorEvent event,
                                    SmartHome smartHome,
                                    CommandSender commandSender);
}
