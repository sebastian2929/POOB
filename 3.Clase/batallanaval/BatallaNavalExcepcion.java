
/**
 * Write a description of class BatllaNavalExcepcion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatallaNavalExcepcion extends Exception
{
    public static final String alNorteE = "la maquina no se puede mover hacia el norte";
    public static final String pilotosE1 = "No es un marino de la flota";
    public static final String pilotosE2 = "No es marino del portaavion";
    public static final String pilotosE3 = "Esta asignado a más de un avión";
    public static final String potenciaE = "Hay menos marinos que maquinas en la flota";
    public static final String potenciaE2 = "más de la mitad de las flotas tienen problemas de potencia";
    public static final String infiltradosE = "la flota no tiene marinos asignados";
    
    public BatallaNavalExcepcion(String mensaje){
        super(mensaje);
    }
}
