package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlarmTest {

    @Test
    void tryToAlarmSignalisationSucceedWhenItIsActivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl();
        signalisation.activate(accessCode);
        // when
        signalisation.alarm();
        // then
        String expectedState = "Alarmed";
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToAlarmSignalisationDoNothingWhenItIsDeactivated() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl();
        // when
        signalisation.alarm();
        // then
        String expectedState = "Deactivated";
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToAlarmSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String accessCode = "0000";
        SignalisationImpl signalisation = new SignalisationImpl();
        signalisation.activate(accessCode);
        signalisation.alarm();
        // when
        signalisation.alarm();
        // then
        String expectedState = "Alarmed";
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState().getName());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
