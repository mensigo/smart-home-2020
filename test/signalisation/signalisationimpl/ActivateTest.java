package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.SignalStateName;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.signalisation.SignalStateName.*;

public class ActivateTest {

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsActivatedAndEnteredCodeIsNull() {
        // given
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        signalisation.activate("0000");
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsDeactivatedAndEnteredCodeIsNull() {
        // given
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsAlarmedAndEnteredCodeIsNull() {
        // given
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        signalisation.alarm();
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsActivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        signalisation.activate(accessCode);
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        SignalStateName expectedState = STATE_ACTIVATED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToActivateSignalisationSucceedAndSetNewAccessCodeWhenItIsDeactivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        SignalStateName expectedState = STATE_ACTIVATED;
        String expectedAccessCode = enteredCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl("0000");
        signalisation.activate(accessCode);
        signalisation.alarm();
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        SignalStateName expectedState = STATE_ALARMED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
