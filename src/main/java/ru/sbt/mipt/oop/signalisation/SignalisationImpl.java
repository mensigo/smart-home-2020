package ru.sbt.mipt.oop.signalisation;

public class SignalisationImpl implements Signalisation {
    private String accessCode;
    private String stateName;

    public SignalisationImpl(String accessCode, String initialSignalStateString) {
        if (accessCode == null) {
            throw new NullPointerException("SignalisationImpl::SignalisationImpl(..) parameter @accessCode is null.");
        } else if (initialSignalStateString == null) {
            throw new NullPointerException("SignalisationImpl::SignalisationImpl(..) parameter @signalStateString is null.");
        }
        this.accessCode = accessCode;
        switch (initialSignalStateString) {
            case ActivatedSignalState.stateName:
                setState(ActivatedSignalState.stateName);
                break;
            case DeactivatedSignalState.stateName:
                setState(DeactivatedSignalState.stateName);
                break;
            case AlarmedSignalState.stateName:
                setState(AlarmedSignalState.stateName);
                break;
        }
    }

    @Override
    public String getState() {
        return stateName;
    }

    // for tests to ensure accessCode changes
    public boolean isAccessCode(String code) {
        return accessCode.equals(code);
    }

    @Override
    public void setState(String state) {
        this.stateName = state;
    }

    @Override
    public void setState(String state, String accessCode) {
        this.stateName = state;
        this.accessCode = accessCode;
    };

    @Override
    public void activate(String enteredCode) {
        if (enteredCode == null) {
            throw new NullPointerException("SignalisationImpl::activate(..) parameter @enteredCode is null.");
        }
        createSignalState(stateName).activate(enteredCode);
    }

    @Override
    public void deactivate(String enteredCode) {
        if (enteredCode == null) {
            throw new NullPointerException("SignalisationImpl::deactivate(..) parameter @enteredCode is null.");
        }
        createSignalState(stateName).deactivate(enteredCode);
    }

    @Override
    public void alarm() {
        createSignalState(stateName).alarm();
    }

    private SignalState createSignalState(String signalStateName) {
        switch (signalStateName) {
            case ActivatedSignalState.stateName:
                return new ActivatedSignalState(accessCode, this);
            case DeactivatedSignalState.stateName:
                return new DeactivatedSignalState(accessCode, this);
            case AlarmedSignalState.stateName:
                return new AlarmedSignalState(accessCode, this);
            default:
                throw new IllegalArgumentException("SignalisationImpl::createSignalState(..) parameter @signalStateName has wrong SignalState type..");
        }
    }
}
