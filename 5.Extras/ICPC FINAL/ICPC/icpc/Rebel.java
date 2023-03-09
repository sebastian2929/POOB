package icpc;


/**
 * Es un tipo de ruta la cual no permite agregar sennales.
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Rebel extends Ruta
{
    /**
     * Constructor for objects of class rebel
     */
    public Rebel(Interseccion interseccionA, Interseccion interseccionB){
        super(interseccionA, interseccionB);
        type = "rebel";
        color = "orange";
        super.changeColor(color);
    }
}
