package ru.sbt.mipt.oop.signalisation;

public class AlarmedSignalState implements SignalState {
    public static final String stateName = "AlarmedSignalState";
    private final String accessCode;
    private final Signalisation signalisation;

    public AlarmedSignalState(String accessCode, Signalisation signalisation) {
        if (accessCode == null) {
            throw new NullPointerException("AlarmedSignalState::AlarmedSignalState(..) parameter @accessCode is null.");
        } else if (signalisation == null) {
            throw new NullPointerException("AlarmedSignalState::AlarmedSignalState(..) parameter @signalisation is null.");
        }
        this.accessCode = accessCode;
        this.signalisation = signalisation;
    }

    @Override
    public void activate(String enteredCode) {
        System.out.println("Signalisation has been already activated. Alarm is on.");
    }

    @Override
    public void deactivate(String enteredCode) {
        if (accessCode.equals(enteredCode)) {
            System.out.println("Signalisation has been successfully deactivated. Alarm is off.");
            signalisation.setState(DeactivatedSignalState.stateName);
        } else {
            System.out.println("Fail to deactivate signalisation: invalid code entered. Alarm is on.");
        }
    }

    @Override
    public void alarm() {
        System.out.println("Signalisation has been already alarmed. Alarm is on.");
    }
}
