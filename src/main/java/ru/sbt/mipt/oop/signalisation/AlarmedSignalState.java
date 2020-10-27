package ru.sbt.mipt.oop.signalisation;

import static ru.sbt.mipt.oop.signalisation.SignalStateName.STATE_ALARMED;

public class AlarmedSignalState implements SignalState {
    private final SignalStateName name;
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
        this.name = STATE_ALARMED;
    }

    @Override
    public SignalStateName getName() {
        return name;
    }

    @Override
    public void activate(String enteredCode) {
        System.out.println("Signalisation has been already activated. Alarm is on.");
    }

    @Override
    public void deactivate(String enteredCode) {
        if (accessCode.equals(enteredCode)) {
            System.out.println("Signalisation has been successfully deactivated. Alarm is off.");
            signalisation.setState(new DeactivatedSignalState(accessCode, signalisation));
        } else {
            System.out.println("Fail to deactivate signalisation: invalid code entered. Alarm is on.");
        }
    }

    @Override
    public void alarm() {
        System.out.println("Signalisation has been already alarmed. Alarm is on.");
    }
}
