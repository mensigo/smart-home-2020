package ru.sbt.mipt.oop.signalisation;

public class DeactivatedSignalState implements SignalState {
    private final String accessCode;
    private final SignalisationImpl signalisation;

    public DeactivatedSignalState(String accessCode, SignalisationImpl signalisation) {
        if (accessCode == null) {
            throw new NullPointerException("DeactivatedSignalState::DeactivatedSignalState(..) parameter @accessCode is null.");
        } else if (signalisation == null) {
            throw new NullPointerException("DeactivatedSignalState::DeactivatedSignalState(..) parameter @signalisation is null.");
        }
        this.accessCode = accessCode;
        this.signalisation = signalisation;
    }

    @Override
    public void activate(String enteredCode) {
        System.out.println("Signalisation has been successfully activated with code " + enteredCode);
        signalisation.setState(new ActivatedSignalState(accessCode, signalisation), enteredCode);
    }

    @Override
    public void deactivate(String enteredCode) {
        System.out.println("Signalisation has been already deactivated.");
    }

    @Override
    public void alarm() {
        System.out.println("Fail to alarm: signalisation is deactivated.");
    }
}
