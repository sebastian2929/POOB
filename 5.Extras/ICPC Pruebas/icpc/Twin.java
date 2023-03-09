package icpc;
import shapes.*;

/**
 * Write a description of class Twin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Twin extends Sennal

{
    /**
     * Constructor for objects of class Twin
     */
    public Twin(Interseccion interseccionA,Interseccion interseccionB,int SpeedLimit,int numero)
    {
        super(interseccionA,interseccionB,SpeedLimit);
        sennal.changeColor("4"); //
        if(numero == 1){
            sennal.setPosition(posXa,posYa);
        }
        if(numero == 2){
            sennal.setPosition(posXb,posYb);
        }
    }
}

