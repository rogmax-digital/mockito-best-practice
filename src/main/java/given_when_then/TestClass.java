package given_when_then;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class HelloService {
    public String sayHello(String user) {
        return "Hello world, " + user;
    }
}

public class TestClass {

    private HelloService helloService = new HelloService();

    @Test
    public void testSayHello() {
        //Given or Arrange
        String user = "HelloUser";

        //When or Act
        String message = helloService.sayHello(user);

        //Then or Assert
        assertThat(message, is("Hello world, HelloUser"));
    }
}

