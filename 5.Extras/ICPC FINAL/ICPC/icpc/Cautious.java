package icpc;
/**
 * Es un tipo de sennal la cual su limite de velocidad sera el limite de velocidad minimo de las rutas del sistema ICPC.
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Cautious extends Sennal
{
    /**
     * Constructor for objects of class Cautious
     */
    
    public Cautious(Interseccion interseccionA,Interseccion interseccionB,int SpeedLimit)
    {
        super(interseccionA,interseccionB,SpeedLimit);
        type = "cautious";
        sennal.setAngle(180);
        sennal.changeColor("blue");
    }
}

