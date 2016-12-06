package argument_captor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TestService {

    private String message;

    public void checkCensor(String message) {
        message = message.replaceAll("white", "all");
        setMessage(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

@RunWith(MockitoJUnitRunner.class)
public class TestClass {

    @Spy
    private TestService testService;
    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Test
    public void testCheckCensor() {
        String message = "I love white cats, they are awesome";

        testService.checkCensor(message);

        verify(testService, times(1)).setMessage(stringCaptor.capture());
        String actualMessage = stringCaptor.getValue();
        assertThat(actualMessage, is("I love all cats, they are awesome"));
//        or if times > 1
//        List<String> messages = stringCaptor.getAllValues();
    }
}