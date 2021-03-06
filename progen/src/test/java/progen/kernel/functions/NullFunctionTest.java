package progen.kernel.functions;

import org.junit.Before;
import org.junit.Test;
import progen.kernel.functions.NullFunction;
import progen.kernel.functions.Terminal;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jirsis
 * @since 2.0
 */
public class NullFunctionTest {

  private NullFunction nullFunction;

  @Before
  public void setUp() {
    nullFunction = new NullFunction("NULL");
  }

  @Test
  public void doubleXTest() {
    assertTrue(nullFunction instanceof Terminal);
    assertEquals(0, nullFunction.getArity());
    assertEquals("", nullFunction.getReturnType());
    assertEquals("", nullFunction.getSignature());
    assertEquals("NULL", nullFunction.getSymbol());

  }

  @Test
  public void evaluateTest() {
    List<Node> arguments = new ArrayList<Node>();
    UserProgram userProgram = null;
    HashMap<String, Object> returnAddr = null;
    assertNull(nullFunction.evaluate(arguments, userProgram, returnAddr));

    nullFunction.setValue(3);
    assertNull(nullFunction.getValue());
    assertNull(nullFunction.evaluate(arguments, userProgram, returnAddr));
  }

}
