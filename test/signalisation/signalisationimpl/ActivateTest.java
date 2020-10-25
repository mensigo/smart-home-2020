package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.*;

public class ActivateTest {

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsActivatedAndEnteredCodeIsNull() {
        // given
        SignalisationImpl signalisation = new SignalisationImpl();
        signalisation.activate("0000");
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsDeactivatedAndEnteredCodeIsNull() {
        // given
        SignalisationImpl signalisation = new SignalisationImpl();
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsAlarmedAndEnteredCodeIsNull() {
        // given
        SignalisationImpl signalisation = new SignalisationImpl();
        signalisation.alarm();
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsActivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl();
        signalisation.activate(accessCode);
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        String expectedState = "Activated";
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToActivateSignalisationSucceedAndSetNewAccessCodeWhenItIsDeactivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl();
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        String expectedState = "Activated";
        String expectedAccessCode = enteredCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl();
        signalisation.activate(accessCode);
        signalisation.alarm();
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        String expectedState = "Alarmed";
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
