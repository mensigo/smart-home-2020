package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        String expectedAccessCode = accessCode;
        assertTrue(signalisation.isActivated());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToActivateSignalisationSucceedAndSetNewAccessCodeWhenItIsDeactivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        String expectedAccessCode = enteredCode;
        assertTrue(signalisation.isActivated());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        signalisation.alarm();
        // when
        String enteredCode = "1234";
        signalisation.activate(enteredCode);
        // then
        String expectedAccessCode = accessCode;
        assertTrue(signalisation.isAlarmed());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
