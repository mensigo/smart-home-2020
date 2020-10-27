package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.SignalStateName;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.signalisation.SignalStateName.*;

public class AlarmTest {

    @Test
    void tryToAlarmSignalisationSucceedWhenItIsActivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        // when
        signalisation.alarm();
        // then
        SignalStateName expectedState = STATE_ALARMED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToAlarmSignalisationDoNothingWhenItIsDeactivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        // when
        signalisation.alarm();
        // then
        SignalStateName expectedState = STATE_DEACTIVATED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToAlarmSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode);
        signalisation.activate(accessCode);
        signalisation.alarm();
        // when
        signalisation.alarm();
        // then
        SignalStateName expectedState = STATE_ALARMED;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
