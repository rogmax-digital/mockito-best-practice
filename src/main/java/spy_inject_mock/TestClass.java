package spy_inject_mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class TestService {
    public int firstMethod() {
//        some logic
        return secondMethod();
    }

    public int secondMethod() {
//        complex logic
        return 1;
    }
}

@RunWith(MockitoJUnitRunner.class)
public class TestClass {
    @Spy
    @InjectMocks
    private TestService testService;

    @Test
    public void testFirstMethod() {
        doReturn(2).when(testService).secondMethod();

        int result = testService.firstMethod();

        assertThat(result, is(2));
        verify(testService, times(1)).secondMethod();
    }
}