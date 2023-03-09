package domain;
/**
 * Write a description of class IEMOISException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IEMOISException extends Exception
{
    public static final String COURSE_NO_PRICE = "El curso no tiene precio establecido";
    public static final String SPECIALIZATION_EMPTY = "No hay una especializacion";
    public static final String COURSE_ERROR_PRICE = "El precio es incorrecto";
    public static final String COURSE_ERROR_NAME = "No existe un curso con ese nombre";
    public static final String COURSE_SAME_NAME = "Ya existe ese nombre";

    /**
     * Constructor for objects of class IEMOISException
     */
    public IEMOISException(String mensaje)
    {
        super(mensaje);
    }
}
