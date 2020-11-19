package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.SignalisationImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String expectedAccessCode = accessCode;
        assertTrue(signalisation.isAlarmed());
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
        String expectedAccessCode = accessCode;
        assertTrue(signalisation.isDeactivated());
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
        String expectedAccessCode = accessCode;
        assertTrue(signalisation.isAlarmed());
        assertTrue(signalisation.isAccessCode(expectedAccessCode));
    }
}
