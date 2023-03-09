package icpc;

/**
 * Es un tipo de interseccion la cual solo puede tener una ruta. 
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Hermit extends Interseccion
{   
    /**
     * Constructor for objects of class Hermit
     */
    public Hermit(String color, int x, int y)
    {
        super(color,x,y);
        type = "hermit";
        changeDiameter(37);
    }
}
