package icpc;
import shapes.*;
import java.util.ArrayList;

/**
 * Lugar de inicio o fin de una ruta. 
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 1/11/22
 */
public class Interseccion
{
    private Circle interseccion;
    protected String type = "normal";
    protected ArrayList<Ruta> interseccionRutas = new ArrayList<Ruta>();

    /**
     * Constructor de los objetos la clase Interseccion.
     * @param String color, Color de la interseccion.
     * @param int x, Posicion x de la interseccion.
     * @param int y, Posicion y de la interseccion.
     */
    public Interseccion(String color, int x, int y)
    {
        this.interseccion = new Circle();
        interseccion.changeColor(color);
        interseccion.moveHorizontal(x);
        interseccion.moveVertical(y);
    }
    
    /**
     * Metodo que hace visible la interseccion.
     */
    public void makeVisible(){
        this.interseccion.makeVisible();
    }
    
    /**
     * Metodo que hace invisible la interseccion.
     */
    public void makeInvisible(){
        this.interseccion.makeInvisible();
    }
    
    /**
    * Metodo que obtiene la posicion x de la inteterseccion.
    * @return posicionX, PosicionX de la interseccion.
    */
    public int getPositionx(){
        return this.interseccion.getPositionx();
    }
    
    /**
    * Metodo que obtiene la posicion y de la inteterseccion.
    * @return posicionY, PosicionY de la interseccion.
    */
    public int getPositiony(){
        return this.interseccion.getPositiony();
    }
    
    /**
     * Metodo que verifica que la interseccion este relacionada con la ruta y si no lo esta la agrega a su arrayList de rutas.
     */
    public void verificaRuta(Ruta ruta){
        if(!interseccionRutas.contains(ruta)){
           interseccionRutas.add(ruta);
        }
    }
    
    /**
     * Metodo que obtiene el ArrayList de rutas de cada interseccion.
     * @return ArrayList<Ruta> rutas, rutas de la interseccion.
     */
    public ArrayList<Ruta> getRutas(){
        return interseccionRutas;
    }
    
    /**
     * Metodo que modifica el arraylist de rutas de una interseccion.
     * @param ArrrayList<Ruta> rutas , rutas de la interseccion.
     */
    public void setRutas(ArrayList<Ruta> rutas){
        interseccionRutas = rutas;
    }
    
    /**
     * Metodo que borra una ruta del ArrayList de rutas de una interseccion.
     */
    public void removeRuta(Ruta ruta){
        interseccionRutas.remove(ruta);
    }
    
    /**
     * Metodo que obtiene el tamanno del arrayList de rutas
     * @return int size, tammano del arryList.
     */
    public int getSize(){
        return interseccionRutas.size();
    }
    
    /**
     * Metodo que retorna el tipo de interseccion.
     * @return String type, tipo de la interseccion.
     */
    public String getType(){
        return type;
    }
    
    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeDiameter(int newDiameter){
        this.interseccion.changeSize(newDiameter);
    }
}
