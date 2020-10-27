package ru.sbt.mipt.oop.lib.coolcompany;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.objects.SmartHome;

public class SensorEventsManagerAdapterImpl implements SensorEventsManagerAdapter {
    private final SensorEventsManager sensorEventsManager;
    private final CCEventTypeTranslator ccEventTypeTranslator;
    private final SmartHome smartHome;

    public SensorEventsManagerAdapterImpl(SensorEventsManager sensorEventsManager,
                                          CCEventTypeTranslator ccEventTypeTranslator,
                                          SmartHome smartHome) {
        this.sensorEventsManager = sensorEventsManager;
        this.ccEventTypeTranslator = ccEventTypeTranslator;
        this.smartHome = smartHome;
    }

    @Override
    public void registerEventHandler(EventHandler handler) {
        // handler(SensorEvent event, SmartHome smartHome) --> ccHandler(CCSensorEvent ccEvent)
        sensorEventsManager.registerEventHandler(ccEvent -> {
            SensorEvent event = ccEventTypeTranslator.translate(ccEvent);
            handler.handleEvent(event, smartHome);
        });
    }

    @Override
    public void start() {
        sensorEventsManager.start();
    }
}
