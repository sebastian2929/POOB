package icpc;


/**
 * Es un tipo de ruta la cual no se puede borrar por la tanto sus intersecciones tampoco.
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Fixed extends Ruta
{
    /**
     * Constructor for objects of class Fixed
     */
    
    public Fixed(Interseccion interseccionA, Interseccion interseccionB)
    {
        super(interseccionA, interseccionB);
        type = "fixed";
        color = "cyan";
        changeColor(color);
    }
}
