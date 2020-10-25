package ru.sbt.mipt.oop.signalisation;

public class ActivatedSignalState implements SignalState {
    private final String name;
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
        this.name = "Activated";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void activate(String enteredCode) {
        System.out.println("Signalisation has been already activated.");
    }

    @Override
    public void deactivate(String enteredCode) {
        if (accessCode.equals(enteredCode)) {
            System.out.println("Signalisation has been successfully deactivated.");
            signalisation.setState(new DeactivatedSignalState(accessCode, signalisation));
        } else {
            System.out.println("Fail to deactivate signalisation: invalid code entered. Alarming..");
            signalisation.setState(new AlarmedSignalState(accessCode, signalisation));
        }
    }

    @Override
    public void alarm() {
        signalisation.setState(new AlarmedSignalState(accessCode, signalisation));
        System.out.println("Signalisation has been successfully alarmed. Alarm is on.");
    }
}
