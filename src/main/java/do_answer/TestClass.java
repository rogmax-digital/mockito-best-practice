package do_answer;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doAnswer;

@Setter
@Getter
class TestData {
    int field = 42;
}

class TestService {
    public String convert(TestData testData) {
        //some convert
        return null;
    }
}

@RunWith(MockitoJUnitRunner.class)
public class TestClass {
    @Mock
    private TestService testService;

    @Test
    public void testConvert() {
        TestData testData = new TestData();

        doAnswer(args -> {
            TestData innerTestData = (TestData) args.getArguments()[0];
            innerTestData.setField(125);
            return "success";
        }).when(testService).convert(testData);


        String result = testService.convert(testData);


        assertThat(result, is("success"));
        assertThat(testData.getField(), is(125));
    }
}
