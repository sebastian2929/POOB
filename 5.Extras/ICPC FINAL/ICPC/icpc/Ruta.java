package icpc;
import shapes.*;
/**
 * Un camino que se crea para unir dos intersecciones.
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Ruta
{
    private Rectangle ruta;
    private Integer limiteVelocidad = -1;
    protected Sennal sennal = null;
    protected String type = "normal";
    protected String color = "gray";
    protected int posX = 0;
    protected int posY = 0;
    /**
     * Constructor de los objetos de la clase ruta.
     * @param Interseccion interseccionA, Interseccion A (punto inicial de la ruta).
     * @param Interseccion interseccionB, Interseccion B (punto final de la ruta).
     */
    public Ruta(Interseccion interseccionA, Interseccion interseccionB){
        this.ruta = new Rectangle();
        int posAx = interseccionA.getPositionx();
        int posAy = interseccionA.getPositiony();
        int posBx = interseccionB.getPositionx();
        int posBy = interseccionB.getPositiony();
        int numerador = posBy-posAy;
        int denominador = posBx-posAx;
        int distancia = getDistance(denominador,numerador);
        int angulo = getAngle(posAx,posAy,posBx,posBy);
        int mayorX = posAx > posBx ? posBx : posAx;
        posX = mayorX;
        int mayorY = setPosMaxY(posAx,posAy,posBx,posBy,mayorX);
        posY = mayorY;
        ruta.setPosition((mayorX +12),(mayorY-17));
        ruta.changeSize(6,distancia);
        ruta.setAngle(angulo);
    }
    
    /**
     * Metodo que hace visible la ruta.
     */
    public void makeVisible(){
        this.ruta.makeVisible();
    }
    
    /**
     * Metodo que hace invisible la ruta.
     */
    public void makeInvisible(){
        this.ruta.makeInvisible();
    }
    
    /**
     * Metodo que asigna un limite de velocidad a una ruta;
     * @param int speedLimit, Limite de velocidad de la ruta;
     */
    public void setLimiteVelocidad(int speedLimit){
        limiteVelocidad = speedLimit;
    }
    
    /**
     * Metodo que asigna una sennal a una ruta;
     * @param Sennal sennal, Sennal de la ruta;
     */
    public void setSennal(Sennal sennal){
        this.sennal = sennal;
    }
    
    /**
     * Metodo que obtiene el limite de velocidad de la ruta.
     */
    public int getLimiteVelocidad(){
        return limiteVelocidad;
    }
    
    /**
     * Metodo que obtiene la sennal de la ruta.
     */
    public Sennal getSennalRuta(){
        return sennal;
    }
    
    /**
     * Metodo que retorna el tipo de ruta.
     * @return String type, tipo de la ruta.
     */
    public String getType(){
        return type;
    }
    
    /*
     * Metodo que define la posicion Y en la que se va a ubicar la ruta.
     * @param int posAx, Posicion x de la interseccion A.
     * @param int posBx, Posicion x de la interseccion B.
     * @param int posAy, Posicion y de la interseccion A.
     * @param int posBy, Posicion y de la interseccion B.
     * @return posicion, Posiciion Y en la que se va a ubicar la ruta.
     */
    private int setPosMaxY(int posAx,int posAy, int posBx, int posBy,int mayorX){
        int posicion = 0;
        if(posAx == posBx && posAy > posBy){
            posicion = posAy;
        }
        if(posAx == posBx && posAy < posBy){
            posicion = posBy;
        }
        else{
            posicion = mayorX == posAx ? posAy : posBy;
        }
        return posicion;
    }
    
    /*
     * Metodo que define la pendiente entre dos puntos.
     * @param int posAx, Posicion x de la interseccion A.
     * @param int posAy, Posicion x de la interseccion B.
     * @param int posBx, Posicion y de la interseccion A.
     * @param int posBy, Posicion y de la interseccion B.
     * @return slope, Pendiente.
     */
    private double getSlope(int posAx,int posAy , int posBx, int posBy){
        double numerador;
        double denominador;
        double pendiente;
        numerador = (posBy - posAy);
        denominador =(posBx - posAx);
        pendiente = numerador / denominador;
        return pendiente * -1;
    }
    
    /*
     * Metodo que defene el angulo entre dos puntos.
     * @param int posAx, Posicion x de la interseccion A.
     * @param int posAy, Posicion x de la interseccion B.
     * @param int posBx, Posicion y de la interseccion A.
     * @param int posBy, Posicion y de la interseccion B.
     * @return angle, Angulo.
     */
    private int getAngle(int posAx,int posAy, int posBx, int posBy){
        int angle;
        double pendiente = getSlope(posAx,posAy,posBx,posBy);
        if((posBx - posAx)== 0 ){
            angle = posAx < posBx ? 270 : 90;
        }
        else{
        angle = (int)(Math.toDegrees(Math.atan(pendiente)));
        }
        return angle;
    }
    
    /*
     * Metodo que obtine la distancia entre dos puntos.
     * @param int point1, Punto uno.
     * @param int point2, Punto dos.
     * @return distance, La distancia entre los dos puntos.
     */
    private int getDistance(int point1, int point2){
        int distance;
        distance = (int)(Math.sqrt(Math.pow((point1),2) + Math.pow((point2),2)));
        return distance;
    }
    
    /*
     * Cambia el color de la ruta.
     * @param Strin color, nuevo color de la ruta.
     */
    protected void changeColor(String color){
        ruta.changeColor(color);
    }
}
