package progen.kernel.functions;

import org.junit.Before;
import org.junit.Test;
import progen.kernel.functions.IntM;
import progen.kernel.functions.IntN;
import progen.kernel.functions.NonTerminal;
import progen.kernel.functions.ShiftSignedLeft;
import progen.kernel.grammar.GrammarNonTerminalSymbol;
import progen.kernel.grammar.GrammarTerminalSymbol;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author jirsis
 * @since 2.0
 */
public class ShiftSignedLeftTest {

  private ShiftSignedLeft shift;

  @Before
  public void setUp() {
    shift = new ShiftSignedLeft();
  }

  @Test
  public void andTest() {
    assertTrue(shift instanceof NonTerminal);
    assertEquals(2, shift.getArity());
    assertEquals("int", shift.getReturnType());
    assertEquals("int$$int$$int", shift.getSignature());
    assertEquals("<<", shift.getSymbol());

  }

  @Test
  public void evaluateTest() {
    List<Node> arguments = new ArrayList<Node>();
    UserProgram userProgram = null;
    HashMap<String, Object> returnAddr = null;

    Node a = new Node(new GrammarNonTerminalSymbol("Ax", shift.getReturnType().toString()));
    Node b = new Node(new GrammarNonTerminalSymbol("Ax", shift.getReturnType().toString()));

    IntN x = new IntN();
    x.setValue(0x12);
    GrammarTerminalSymbol x12 = new GrammarTerminalSymbol(x);
    IntM y = new IntM();
    y.setValue(0x2);
    GrammarTerminalSymbol x2 = new GrammarTerminalSymbol(y);

    arguments.add(a);
    arguments.add(b);

    //0x12 << 0x2
    a.setFunction(x12);
    b.setFunction(x2);
    assertEquals(0x48, (Integer) shift.evaluate(arguments, userProgram, returnAddr), 0);

  }

}
