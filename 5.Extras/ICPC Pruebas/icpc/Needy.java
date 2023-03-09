package icpc;
import java.util.ArrayList;


/**
 * Write a description of class Need here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
