package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.ActivatedSignalState;
import ru.sbt.mipt.oop.signalisation.AlarmedSignalState;
import ru.sbt.mipt.oop.signalisation.DeactivatedSignalState;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.*;

public class ActivateTest {

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsActivatedAndEnteredCodeIsNull() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, ActivatedSignalState.stateName);
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsDeactivatedAndEnteredCodeIsNull() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, DeactivatedSignalState.stateName);
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationThrowNullPointerExceptionWhenItIsAlarmedAndEnteredCodeIsNull() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, AlarmedSignalState.stateName);
        // when&then
        String enteredCode = null;
        assertThrows(NullPointerException.class, () -> signalisation.activate(enteredCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsActivated() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, ActivatedSignalState.stateName);
        // when
        String enteredCode = "0000";
        signalisation.activate(enteredCode);
        // then
        String expectedState = ActivatedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToActivateSignalisationSucceedAndSetNewAccessCodeWhenItIsDeactivated() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, DeactivatedSignalState.stateName);
        // when
        String enteredCode = "0000";
        signalisation.activate(enteredCode);
        // then
        String expectedState = ActivatedSignalState.stateName;
        String expectedAccessCode = enteredCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToActivateSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, AlarmedSignalState.stateName);
        // when
        String enteredCode = "0000";
        signalisation.activate(enteredCode);
        // then
        String expectedState = AlarmedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
