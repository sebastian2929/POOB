import java.util.ArrayList;

/**
 * Write a description of class Barco here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barco extends Maquina
{
    private ArrayList<Marino> marinos = new ArrayList<Marino>();
    private int numero;
    
    /**
     * Consulta las maquinas que pueden afectarse por una explosion en la posicion dada
     * En una coordenada pueden estar muchas maquinas.
     * Los aviones en Aire no se destruyen
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return boolean sobre si seran destruidas
     */
    public boolean seranDestruidas(int longitud, int latitud){
        boolean bandera;
        bandera = u.seranDestruidas(longitud,latitud);
        return bandera;
    }
    
    /**
     * Consulta las maquinas debiles de una flota
     * Un barco es debil si tiene menos de 5 marinos
     * Un avion es debil si no tiene piloto principal
     * Un portaaviones es debil si es un barco debil o alguno de sus aviones en aire es debil
     * @Return un boolean si son debiles
     */
    public boolean maquinasDebiles(){
        int tamanno = marinos.size();
        boolean bandera = false;
        if(tamanno < 5){
            bandera = true;
        }
        return bandera;
    }
    
    /**
     * Las maquinas estan preparadas para autodestruirse
     * Los barcos y aviones se auto destruyen si reciben la instruccion
     * Las capsulas se auto destruyen si su nodriza es destruida
     * @Param instruccion, dice si se destruyen o no
     */
    public void autoDestruirse(boolean instruccion){
        if(instruccion){
            autoDes = true;
            System.out.println("me autodestrui por ordenes superiores");
        }
    }
    
    /**
     * Obtiene los pilotos que son marinos
     * @Return arreglo de marinos
     */
    public ArrayList<Marino> getMarinos(){
        return marinos;
    }
}

