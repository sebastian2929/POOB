package domain;

/**
 * Clase de las excepciones de la aplicacion
 * 
 * @version 0.1
 */
public class daPOOsException extends Exception {

    public static final String NO_ES_TU_FICHA = "No es tu ficha, selecciona la correcta.";
    public static final String NO_SE_PUEDE_MOVER = "No se puede mover, selecciona una ficha correcta.";
    public static final String ARCHIVO_NO_ENCONTRADO = "Archivo no encontrado.";

    public daPOOsException(String message) {
        super(message);

    }
}
