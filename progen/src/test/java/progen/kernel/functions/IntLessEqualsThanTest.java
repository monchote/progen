package progen.kernel.functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import progen.kernel.grammar.GrammarNonTerminalSymbol;
import progen.kernel.grammar.GrammarTerminalSymbol;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * @author jirsis
 * @since 2.0
 */
public class IntLessEqualsThanTest {

  private IntLessEqualsThan lessEqualsThan;

  @Before
  public void setUp() {
    lessEqualsThan = new IntLessEqualsThan();
  }

  @Test
  public void lessEqualsThanTest() {
    assertTrue(lessEqualsThan instanceof NonTerminal);
    assertEquals(2, lessEqualsThan.getArity());
    assertEquals("boolean", lessEqualsThan.getReturnType());
    assertEquals("boolean$$int$$int", lessEqualsThan.getSignature());
    assertEquals("<=", lessEqualsThan.getSymbol());

  }

  @Test
  public void evaluateTest() {
    List<Node> arguments = new ArrayList<Node>();
    UserProgram userProgram = null;
    HashMap<String, Object> returnAddr = null;

    Node a = new Node(new GrammarNonTerminalSymbol("Ax", lessEqualsThan.getReturnType().toString()));
    Node b = new Node(new GrammarNonTerminalSymbol("Ax", lessEqualsThan.getReturnType().toString()));

    IntN x = new IntN();
    x.setValue(1);
    GrammarTerminalSymbol one = new GrammarTerminalSymbol(x);
    IntM y = new IntM();
    y.setValue(2);
    GrammarTerminalSymbol two = new GrammarTerminalSymbol(y);

    arguments.add(a);
    arguments.add(b);

    //1<=2?
    a.setFunction(one);
    b.setFunction(two);
    assertTrue((Boolean) lessEqualsThan.evaluate(arguments, userProgram, returnAddr));

    //2<=1?
    a.setFunction(two);
    b.setFunction(one);
    assertFalse((Boolean) lessEqualsThan.evaluate(arguments, userProgram, returnAddr));

    //1<=1?
    a.setFunction(one);
    b.setFunction(one);
    assertTrue((Boolean) lessEqualsThan.evaluate(arguments, userProgram, returnAddr));

    //2<=2?
    a.setFunction(two);
    b.setFunction(two);
    assertTrue((Boolean) lessEqualsThan.evaluate(arguments, userProgram, returnAddr));
  }

}
