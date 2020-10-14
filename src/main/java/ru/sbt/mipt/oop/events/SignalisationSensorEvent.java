package ru.sbt.mipt.oop.events;

public class SignalisationSensorEvent implements SensorEvent {
    private final SensorEventType type;
    private final String enteredCode;

    public SignalisationSensorEvent(SensorEventType type, String enteredCode) {
        this.type = type;
        this.enteredCode = enteredCode;
    }

    @Override
    public SensorEventType getType() {
        return type;
    }

    @Override
    public String getObjectId() {
        return null;
    }

    @Override
    public boolean isQuiet() {
        return false;
    }

    public String getEnteredCode() {
        return enteredCode;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", enteredCode=" + enteredCode +
                '}';
    }
}
