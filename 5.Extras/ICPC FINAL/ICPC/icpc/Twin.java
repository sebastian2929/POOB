package icpc;
import shapes.*;

/**
 * Es un tipo de sennal la cual crea dos sennales identicas ubicadas a en los extremos de la ruta. 
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Twin extends Sennal

{
    /**
     * Constructor for objects of class Twin
     */
    public Twin(Interseccion interseccionA,Interseccion interseccionB,int SpeedLimit,int numero)
    {
        super(interseccionA,interseccionB,SpeedLimit);
        type = "twin";
        sennal.changeColor("green");
        if(numero == 1){
            sennal.setPosition(posXa,posYa);
        }
        if(numero == 2){
            sennal.setPosition(posXb,posYb);
        }
    }
}

