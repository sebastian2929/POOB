package icpc;
import shapes.*;
/**
 * Representacion usada para identificar la velocidad maxima a la cual se puede ir en una ruta.
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Sennal{
    protected String type = "normal";
    protected Triangle sennal;
    protected Integer limiteVelocidad = null;
    protected int posXa;
    protected int posYa;
    protected int posXb;
    protected int posYb;
    /**
     * Constructor de los objetos de la clase Sennal
     * @param Interseccion interseccionA, Interseccion A (punto inicial de la ruta).
     * @param Interseccion interseccionB, Interseccion B (punto final de la ruta).
     * @param int SpeedLimit, limte de velocidad de la sennal.
     */
    public Sennal(Interseccion interseccionA,Interseccion interseccionB,int SpeedLimit){
        this.sennal = new Triangle();
        posXa = interseccionA.getPositionx();
        posYa = interseccionA.getPositiony();
        posXb = interseccionB.getPositionx();
        posYb = interseccionB.getPositiony();
        int positionX = puntoMedio(posXa,posXb)+15;
        int positionY = puntoMedio(posYa,posYb)-22;
        sennal.setPosition(positionX,positionY);
        limiteVelocidad = SpeedLimit;
    }
    
    /**
     * Metodo que hace visible la sennal.
     */
    public void makeVisible(){
        this.sennal.makeVisible();
    }
    
    /**
     * Metodo que hace invisible la sennal.
     */
    public void makeInvisible(){
        this.sennal.makeInvisible();
    }
    
    /**
     * Metodo que asigna un limite de velocidad a una sennal;
     * @param int speedLimit, Limite de velocidad de la sennal;
     */
    public void setLimiteVelocidad(int speedLimit){
        limiteVelocidad = speedLimit;
    }
    
    /**
     * Metodo que obtiene el limite de velocidad de la sennal;
     */ 
    public Integer getLimiteVelocidad(){
        return limiteVelocidad;
    }
    
    /**
     * Metodo que cambia el color de una sennal.
     * @param String color, Color nuevo de la sennal.
     */
    public void changeColor(String color){
        this.sennal.changeColor(color);
    }
    
    /**
     * Metodo que cambia la posicion de una sennal.
     * @param int newPositionx, posicion en x.
     * @param int newPositiony, posicion en y.
     */
    public void changePosition(int newPositionx,int newPositiony){
        this.sennal.setPosition(newPositionx,newPositiony);
    }
    
    /**
     * Metodo que cambia el angulo de una sennal
     * @param int newAngle, nuevo angulo de la sennal.
     */
    public void setAngle(int newAngle) {
        this.sennal.setAngle(newAngle);
    }
    
    /*
     * Metodo que obtiene el punto medio entre dos posiciones.
     * @return in puntoMedio, Punto medio.
     */
    private int puntoMedio(int posA,int posB){
        int puntoMedio = (posA + posB)/2;
        return puntoMedio;
    }
}
