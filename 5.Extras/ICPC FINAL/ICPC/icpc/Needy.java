package icpc;
import java.util.ArrayList;


/**
 * Es un tipo de interseccion la cual desaparece cuando se queda sin rutas.
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Needy extends Interseccion
{
    /**
     * Constructor for objects of class Need
     */
    public Needy(String color, int x, int y)
    {
       super(color,x,y);
       type = "needy";
       changeDiameter(25);
    }
}
