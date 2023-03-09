
/**
 * Write a description of class Ubicacion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ubicacion
{
    private int longitud;
    private int latitud;
    
    /**
     * Mueve la flota una posicion al norte
     * En el momento que una maquina no se puede mover se para el moviento de toda la flota
     */
    public void alNorte(){
        if(longitud < 180 && longitud > 0){
            longitud++;
        }
    }
    
    /**
     * Mueve todas las maquinas la distancia definida
     * El mundo tablero es circular
     * @Param entero deLon, la longitud de la maquina 
     * @Param entero deLat, la latitud de la maquina 
     */
    public void avance(int deLon, int deLat){
        if(longitud < 180 && longitud > 0){
            longitud = deLon;
        }
        if(latitud < 90 && latitud > -90){
            latitud = deLat;
        }
    }
    
    /**
     * Consulta las maquinas que pueden afectarse por una explosion en la posicion dada
     * En una coordenada pueden estar muchas maquinas.
     * Los aviones en Aire no se destruyen
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return boolean sobre si seran destruidas
     */
    public boolean seranDestruidas(int longitud, int latitud){
        boolean bandera = false;
        if(this.longitud == longitud && this.latitud == latitud){
            bandera = true;
        }
        return bandera;
    }
    
    /**
     * Comprueba si una maquina esta en la posicion dada
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return un boolean comprobando si esta o no en la posicion
     */
    public boolean esBuenAtaque(int longitud, int latitud){
        boolean bandera = false;
        if(this.longitud != longitud && this.latitud != latitud){
            bandera = true;
        }
        return bandera;
    }
    
    /**
     * Mueve todas las maquinas que nos son debiles paso a paso hacia la posicion a atacar indicada por (Lon, Lat)
     * @Param entero Lon, la longitud de la maquina 
     * @Param entero Lat, la latitud de la maquina 
     */
    public void ataquen(int lon, int lat){
        longitud = lon;
        latitud = lat;
    }
    
    /**
     * Obtiene la latitud de la maquina
     * @Return la latitud
     */
    public int getLatitud(){
        return latitud;
    }
    
    /**
     * Obtien la longitud de la maquina
     * @Return la longitud
     */
    public int getLongitud(){
        return longitud;
    }
}
