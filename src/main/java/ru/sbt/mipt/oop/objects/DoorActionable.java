package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.eventhandlers.SmartDoorEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;

public class DoorActionable implements Actionable {
    private final String id;
    private boolean isOpen;
    private String placeName;

    public DoorActionable(boolean isOpen, String id, String placeName) {
        this.isOpen = isOpen;
        this.id = id;
        this.placeName = placeName;
    }

    public String getId() {
        return id;
    }

    public String getPlaceName() { return placeName; }

    public boolean isOpen() { return isOpen; }

    public void handleUsualEvent(SensorEvent event) {
        SmartDoorEventHandler smartDoorEventHandler = new SmartDoorEventHandler();
        smartDoorEventHandler.handleEvent(event, this);
    }

    public void setOpen(boolean open) { isOpen = open; }

    @Override
    public void execute(Action action) {
        action.executeAction(this);
    }
}
