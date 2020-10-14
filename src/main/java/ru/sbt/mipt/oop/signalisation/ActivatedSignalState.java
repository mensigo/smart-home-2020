package ru.sbt.mipt.oop.signalisation;

public class ActivatedSignalState implements SignalState {
    public static final String stateName = "ActivatedSignalState";
    private final String accessCode;
    private final Signalisation signalisation;

    public ActivatedSignalState(String accessCode, Signalisation signalisation) {
        if (accessCode == null) {
            throw new NullPointerException("ActivatedSignalState::ActivatedSignalState(..) parameter @accessCode is null.");
        } else if (signalisation == null) {
            throw new NullPointerException("ActivatedSignalState::ActivatedSignalState(..) parameter @signalisation is null.");
        }
        this.accessCode = accessCode;
        this.signalisation = signalisation;
    }

    @Override
    public void activate(String enteredCode) {
        System.out.println("Signalisation has been already activated.");
    }

    @Override
    public void deactivate(String enteredCode) {
        if (accessCode.equals(enteredCode)) {
            System.out.println("Signalisation has been successfully deactivated.");
            signalisation.setState(DeactivatedSignalState.stateName);
        } else {
            System.out.println("Fail to deactivate signalisation: invalid code entered. Alarming..");
            signalisation.setState(AlarmedSignalState.stateName);
        }
    }

    @Override
    public void alarm() {
        signalisation.setState(AlarmedSignalState.stateName);
        System.out.println("Signalisation has been successfully alarmed. Alarm is on.");
    }
}
