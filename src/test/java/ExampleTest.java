import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExampleTest {


  @Before public void before() {
    //This method is executed before the whole test
  }

  @Test public void testTrue() {
    assertTrue(true);
  }

}
