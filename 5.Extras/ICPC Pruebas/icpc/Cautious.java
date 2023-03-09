package icpc;
/**
 * Write a description of class Cautious here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cautious extends Sennal
{
    /**
     * Constructor for objects of class Cautious
     */
    
    public Cautious(Interseccion interseccionA,Interseccion interseccionB,int SpeedLimit)
    {
        super(interseccionA,interseccionB,SpeedLimit);
        sennal.setAngle(180);
        sennal.changeColor("2"); //blue
    }
}
