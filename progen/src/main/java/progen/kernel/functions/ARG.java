package progen.kernel.functions;

import java.util.List;
import java.util.Map;

import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Representación de los distintos argumentos que puede recibir un ADF.
 * 
 * @author jirsis
 * @since 2.0
 */
public class ARG extends Terminal {

  private static final long serialVersionUID = -4621083319210129287L;
  /** Identificador el ADF del que es argumento. */
  private String idADF;

  /**
   * Constructor por defecto que recibe el nombre del ADF del que es argumento,
   * el identidicador dentro del conjunto de argumentos totales y el tipo de
   * valor que puede almacenar.
   * 
   * @param idADF
   *          Identifiador del ADF del que es argumento.
   * @param signature
   *          Signatura o tipo de valor que almacena.
   * @param argumentPosition
   *          Identificador de la posición que representa dentro del conjunto de
   *          argumentos del ADF.
   */
  public ARG(String idADF, String signature, int argumentPosition) {
    super(signature, "ARG" + argumentPosition);
    this.idADF = idADF;
  }

  @Override
  public Object evaluate(List<Node> arguments, UserProgram userProgram, Map<String, Object> returnAddr) {
    return ((Node) returnAddr.get(idADF + "-" + super.getSymbol())).evaluate(userProgram, returnAddr);
  }
}
