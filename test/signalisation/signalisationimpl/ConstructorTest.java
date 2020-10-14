package signalisation.signalisationimpl;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.signalisation.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTest {

    @Test
    void createSignalisationImplThrowNullPointerExceptionWhenAccessCodeParameterIsNull() {
        // given
        String accessCode = null;
        String signalStateString = DeactivatedSignalState.stateName;
        // when&then
        assertThrows(NullPointerException.class, () -> new SignalisationImpl(accessCode, signalStateString));
    }

    @Test
    void createSignalisationImplThrowNullPointerExceptionWhenSignalStateStringParameterIsNull() {
        // given
        String accessCode = "1234";
        String signalStateString = null;
        // when&then
        assertThrows(NullPointerException.class, () -> new SignalisationImpl(accessCode, signalStateString));
    }

    @Test
    void createSignalisationImplSucceedWhenAccessCodeAndSignalStateStringParamsAreOK() {
        // given
        String accessCode = "1234";
        String signalStateString = DeactivatedSignalState.stateName;
        // when
        SignalisationImpl signalisation = new SignalisationImpl(accessCode, signalStateString);
        // then
        assertNotNull(signalisation);
    }
}
