package exceptions;

/**
 * Excepci�n lanzada cuando ocurre algun error en el acceso a la capa
 * persistente de datos(ficheros, base de datos...)
 * @see
 */
public class AccesoDatosException extends MercadoException{

   public AccesoDatosException(String message) {
        super(message);
    }

}
