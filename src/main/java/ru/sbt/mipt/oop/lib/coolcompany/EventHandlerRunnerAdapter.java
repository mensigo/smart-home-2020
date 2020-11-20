package ru.sbt.mipt.oop.lib.coolcompany;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandlerRunner;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventHandlerRunnerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final CCEventTypeTranslator ccEventTypeTranslator;
    private final EventHandlerRunner eventHandlerRunner;
    private final SmartHome smartHome;

    public EventHandlerRunnerAdapter(CCEventTypeTranslator ccEventTypeTranslator,
                                     EventHandlerRunner eventHandlerRunner,
                                     SmartHome smartHome) {
        this.ccEventTypeTranslator = ccEventTypeTranslator;
        this.eventHandlerRunner = eventHandlerRunner;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent ccEvent) {
        SensorEvent event = ccEventTypeTranslator.translate(ccEvent);
        eventHandlerRunner.runHandlers(event, smartHome);
    }
}
