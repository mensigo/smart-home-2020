package ru.sbt.mipt.oop.signalisation;

public interface Signalisation {
    SignalState getState();

    void setState(SignalState state);

    void setState(SignalState state, String enteredCode);

    void activate(String enteredCode);

    void deactivate(String enteredCode);

    void alarm();
}
