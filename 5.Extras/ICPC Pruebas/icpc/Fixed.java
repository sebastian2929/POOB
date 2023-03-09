package icpc;


/**
 * Write a description of class Fixed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        color = "6"; //cyan
        changeColor(color);
    }
}
