package domain;

/**
 * Excepciones para el AManufacturing
 * 
 * @Autor Andres Arias - Sebastian Blanco
 * @Version 25/11/2022
 */

public class AManufacturingException extends Exception {

    public static final String OPCION_CONSTRUCCION = "Opcion en construccion";
    public static final String ARCHIVO_NO_ENCONTRADO = "Archivo no encontrado";

    /**
     * Constructor de la Excepcion
     * 
     * @param message Mensaje de la excepcion
     */
    public AManufacturingException(String message) {
        super(message);
    }

}
