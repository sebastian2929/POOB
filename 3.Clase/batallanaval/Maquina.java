import java.util.ArrayList;

/**
 * Write a description of class Maquina here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Maquina{
    protected Ubicacion u;
    protected boolean autoDes = false;
    
    /**
     * Mueve la flota una posicion al norte
     * En el momento que una maquina no se puede mover se para el moviento de toda la flota
     */
    public void alNorte(){
        u.alNorte();
    }
    
    /**
     * Mueve todas las maquinas la distancia definida
     * El mundo tablero es circular
     * @Param entero deLon, la longitud de la maquina 
     * @Param entero deLat, la latitud de la maquina 
     */
    public void avance(int deLon, int deLat){
        u.avance(deLon,deLat);
    }
    
    /**
     * Consulta las maquinas que pueden afectarse por una explosion en la posicion dada
     * En una coordenada pueden estar muchas maquinas.
     * Los aviones en Aire no se destruyen
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return boolean sobre si seran destruidas
     */
    public abstract boolean seranDestruidas(int longitud, int latitud);
    
    /**
     * Consulta las maquinas debiles de una flota
     * Un barco es debil si tiene menos de 5 marinos
     * Un avion es debil si no tiene piloto principal
     * Un portaaviones es debil si es un barco debil o alguno de sus aviones en aire es debil
     * @Return un boolean si son debiles
     */
    public abstract boolean maquinasDebiles();
    
    /**
     * Comprueba si una maquina esta en la posicion dada
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return un boolean comprobando si esta o no en la posicion
     */
    public boolean comprobar(int longitud, int latitud){
        boolean bandera = u.esBuenAtaque(longitud,latitud);
        return bandera;
    }
    
    /**
     * Mueve todas las maquinas que nos son debiles paso a paso hacia la posicion a atacar indicada por (Lon, Lat)
     * @Param entero Lon, la longitud de la maquina 
     * @Param entero Lat, la latitud de la maquina 
     */
    public void ataquen(int lon,int lat){
        u.ataquen(lon,lat);    
    }
    
    /**
     * Las maquinas estan preparadas para autodestruirse
     * Los barcos y aviones se auto destruyen si reciben la instruccion
     * Las capsulas se auto destruyen si su nodriza es destruida
     * @Param instruccion, dice si se destruyen o no
     */
    public abstract void autoDestruirse(boolean instruccion);
    
    /**
     * Obtiene su estado
     * @Return un booleano que verifica si esta destruido
     */
    public boolean getAutoDes(){
        return autoDes;
    } 
    
    /**
     * Obtiene la latitud de la maquina
     * @Return la latitud
     */
    public int getLatitud(){
        return u.getLatitud();
    }
    
    /**
     * Obtien la longitud de la maquina
     * @Return la longitud
     */
    public int getLongitud(){
        return u.getLongitud();
    }
    
    /**
     * Consulta los pilotos de flota
     * @Return los pilotos asignados a los aviones de las flotas
     */
    public ArrayList<Marino> pilotos(){
        return new ArrayList<Marino>();
    }
    
    /**
     * Obtiene los marinos
     * @Return un arreglo de marinos
     */
    public ArrayList<Marino> getMarinos(){
        return new ArrayList<Marino>();
    }
}
