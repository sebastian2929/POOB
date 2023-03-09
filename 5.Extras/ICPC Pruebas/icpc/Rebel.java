package icpc;


/**
 * Write a description of class rebel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rebel extends Ruta
{
    /**
     * Constructor for objects of class rebel
     */
    public Rebel(Interseccion interseccionA, Interseccion interseccionB){
        super(interseccionA, interseccionB);
        type = "rebel";
        color = "8"; //ornage
        super.changeColor(color);
    }
}
