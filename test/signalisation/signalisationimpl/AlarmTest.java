package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.ActivatedSignalState;
import ru.sbt.mipt.oop.signalisation.AlarmedSignalState;
import ru.sbt.mipt.oop.signalisation.DeactivatedSignalState;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.*;

public class AlarmTest {

    @Test
    void tryToAlarmSignalisationSucceedWhenItIsActivated() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, ActivatedSignalState.stateName);
        // when
        signalisation.alarm();
        // then
        String expectedState = AlarmedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToAlarmSignalisationDoNothingWhenItIsDeactivated() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, DeactivatedSignalState.stateName);
        // when
        signalisation.alarm();
        // then
        String expectedState = DeactivatedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }

    @Test
    void tryToAlarmSignalisationDoNothingWhenItIsAlarmed() {
        // given
        String accessCode = "1234";
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, AlarmedSignalState.stateName);
        // when
        signalisation.alarm();
        // then
        String expectedState = AlarmedSignalState.stateName;
        String expectedAccessCode = accessCode;
        assertEquals(expectedState, signalisation.getState());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
