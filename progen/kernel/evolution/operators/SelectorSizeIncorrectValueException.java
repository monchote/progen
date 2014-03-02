/**
 * 
 */
package progen.kernel.evolution.operators;

import progen.kernel.error.Error;

/**
 * @author jirsis
 * @since 2.0
 *
 */
public class SelectorSizeIncorrectValueException extends RuntimeException {

	/** Para serialización. */
	private static final long serialVersionUID = 7989167828358778207L;

	/**
	 * Constructor de la excepción en la que se recibe por parámetro
	 * el tamaño del selector esperado y el valor actual.
	 * @param expected Valor esperado del selector.
	 * @param provided Valor actual.
	 */
	public SelectorSizeIncorrectValueException(int expected, int provided){
		super(Error.get(26) + "("+provided+"!="+expected+")");
	}
}
