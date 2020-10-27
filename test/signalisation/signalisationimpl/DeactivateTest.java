package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.SignalStateName;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.signalisation.SignalStateName.*;

public class DeactivateTest {

    @Test
    void tryToDeactivateSignalisationThrowNullPointerExceptionWhenItIsActivatedAndEnteredCodeIsNull() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.deactivate(enteredCode));
    }

    @Test
    void tryToDeactivateSignalisationThrowNullPointerExceptionWhenItIsDeactivatedAndEnteredCodeIsNull() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.deactivate(enteredCode));
    }

    @Test
    void tryToDeactivateSignalisationThrowNullPointerExceptionWhenItIsAlarmedAndEnteredCodeIsNull() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        signalisation.alarm();
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.deactivate(enteredCode));
    }

    @Test
    void tryToDeactivateSignalisationSetAlarmWhenItIsActivatedAndEnteredCodeIsInvalid() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        // when
        String enteredCode = "1234";
        signalisation.deactivate(enteredCode);
        // then
        SignalStateName expectedState = STATE_ALARMED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsDeactivatedAndEnteredCodeIsInvalid() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        // when
        String enteredCode = "1234";
        signalisation.deactivate(enteredCode);
        // then
        SignalStateName expectedState = STATE_DEACTIVATED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsAlarmedAndEnteredCodeIsInvalid() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        signalisation.alarm();
        // when
        String enteredCode = "1234";
        signalisation.deactivate(enteredCode);
        // then
        SignalStateName expectedState = STATE_ALARMED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationSucceedWhenItIsActivatedAndEnteredCodeIsValid() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        // when
        String enteredCode = accessCode;
        signalisation.deactivate(enteredCode);
        // then
        SignalStateName expectedState = STATE_DEACTIVATED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationDoNothingWhenItIsDeactivatedAndEnteredCodeIsValid() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        // when
        String enteredCode = accessCode;
        signalisation.deactivate(enteredCode);
        // then
        SignalStateName expectedState = STATE_DEACTIVATED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToDeactivateSignalisationSucceedWhenItIsAlarmedAndEnteredCodeIsValid() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        signalisation.alarm();
        // when
        String enteredCode = accessCode;
        signalisation.deactivate(enteredCode);
        // then
        SignalStateName expectedState = STATE_DEACTIVATED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
