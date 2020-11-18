package ru.sbt.mipt.oop.signalisation;

public class SignalisationImpl implements Signalisation {
    private String accessCode;
    private SignalState state;

    public SignalisationImpl(String accessCode) {
        this.accessCode = accessCode;
        this.state = new DeactivatedSignalState(accessCode, this);
    }

    // for tests
    public boolean isDeactivated() {
        return state instanceof DeactivatedSignalState;
    }

    public boolean isActivated() {
        return state instanceof ActivatedSignalState;
    }

    public boolean isAlarmed() {
        return state instanceof AlarmedSignalState;
    }

    // for tests to ensure accessCode changes
    public boolean isAccessCode(String code) {
        return accessCode.equals(code);
    }

    // for tests
    public void setState(SignalState state) {
        this.state = state;
    }

    public void setState(SignalState state, String accessCode) {
        this.state = state;
        this.accessCode = accessCode;
    }

    @Override
    public void activate(String enteredCode) {
        if (enteredCode == null) {
            throw new NullPointerException("SignalisationImpl::activate(..) parameter @enteredCode is null.");
        }
        state.activate(enteredCode);
    }

    @Override
    public void deactivate(String enteredCode) {
        if (enteredCode == null) {
            throw new NullPointerException("SignalisationImpl::deactivate(..) parameter @enteredCode is null.");
        }
        state.deactivate(enteredCode);
    }

    @Override
    public void alarm() {
        state.alarm();
    }
}
