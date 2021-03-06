package progen.kernel.functions;

import java.util.List;
import java.util.Map;

import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Clase abstracta que representa a los elementos terminales tal y como se
 * entienden en Programación Genética.
 * 
 * Define los métodos necesarios para definir los distintos tipos de elementos
 * terminales.
 * 
 * @author jirsis
 * @since 2.0
 */
public abstract class Terminal extends Function {

  private static final long serialVersionUID = 69970053838836085L;
  /** Valor concreto del terminal del árbol. */
  private Object value;

  /**
   * Constructor por defecto que recibe dos parámetros y llama al constructor de
   * la clase padre
   * 
   * @see Function
   * @param signature
   *          valor de retorno del terminal.
   * @param symbol
   *          con el que se representará el terminal.
   */
  public Terminal(String signature, String symbol) {
    super(signature, symbol);
    value = null;
  }

  /**
   * Define el valor que tendrá dicho terminal.
   * 
   * @param value
   *          el valor del terminal.
   */
  public void setValue(Object value) {
    this.value = value;
  }

  /**
   * Devuelve el valor concreto del terminal.
   * 
   * @return el valor concreto del terminal.
   */
  public Object getValue() {
    return value;
  }

  @Override
  public Object evaluate(List<Node> arguments, UserProgram userProgram, Map<String, Object> returnAddr) {
    return getValue();
  }

}
