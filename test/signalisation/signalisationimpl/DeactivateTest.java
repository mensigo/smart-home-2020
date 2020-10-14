package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.ActivatedSignalState;
import ru.sbt.mipt.oop.signalisation.AlarmedSignalState;
import ru.sbt.mipt.oop.signalisation.DeactivatedSignalState;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.*;

public class DeactivateTest {

    @Test
    void tryToDeactivateSignalisationThrowNullPointerExceptionWhenItIsActivatedAndEnteredCodeIsNull() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, ActivatedSignalState.stateName);
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.deactivate(enteredCode));
    }

    @Test
    void tryToDeactivateSignalisationThrowNullPointerExceptionWhenItIsDeactivatedAndEnteredCodeIsNull() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, DeactivatedSignalState.stateName);
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.deactivate(enteredCode));
    }

    @Test
    void tryToDeactivateSignalisationThrowNullPointerExceptionWhenItIsAlarmedAndEnteredCodeIsNull() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, AlarmedSignalState.stateName);
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.deactivate(enteredCode));
    }

    @Test
    void tryToDeactivateSignalisationSetAlarmWhenItIsActivatedAndEnteredCodeIsInvalid() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, ActivatedSignalState.stateName);
        // when
        String enteredCode = "0000";
        signalisation.deactivate(enteredCode);
        // then
        String expectedState = AlarmedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsDeactivatedAndEnteredCodeIsInvalid() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, DeactivatedSignalState.stateName);
        // when
        String enteredCode = "0000";
        signalisation.deactivate(enteredCode);
        // then
        String expectedState = DeactivatedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsAlarmedAndEnteredCodeIsInvalid() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, AlarmedSignalState.stateName);
        // when
        String enteredCode = "0000";
        signalisation.deactivate(enteredCode);
        // then
        String expectedState = AlarmedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationSucceedWhenItIsActivatedAndEnteredCodeIsValid() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, ActivatedSignalState.stateName);
        // when
        String enteredCode = accessCode;
        signalisation.deactivate(enteredCode);
        // then
        String expectedState = DeactivatedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsDeactivatedAndEnteredCodeIsValid() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, DeactivatedSignalState.stateName);
        // when
        String enteredCode = accessCode;
        signalisation.deactivate(enteredCode);
        // then
        String expectedState = DeactivatedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationSucceedWhenItIsAlarmedAndEnteredCodeIsValid() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, AlarmedSignalState.stateName);
        // when
        String enteredCode = accessCode;
        signalisation.deactivate(enteredCode);
        // then
        String expectedState = DeactivatedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
