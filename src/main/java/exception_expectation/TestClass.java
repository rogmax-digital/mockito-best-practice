package exception_expectation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;

class TestService {
    public void methodWithException() {
    }
}

@RunWith(MockitoJUnitRunner.class)
public class TestClass {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Spy
    private TestService testService;

    @Test
    public void testMethodWithException() {
        doThrow(new RuntimeException("test")).when(testService).methodWithException();
        //...
        exception.expectMessage("test");
        exception.expect(RuntimeException.class);
        testService.methodWithException();
    }

    @Test(expected = RuntimeException.class)
    public void testFail() {
        doThrow(new RuntimeException("test")).when(testService).methodWithException();
        if (true) {
            throw new RuntimeException("exception");
        }
        testService.methodWithException();
    }

}
