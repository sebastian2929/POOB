import java.util.ArrayList;

/**
 * Write a description of class Avion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Avion extends Maquina
{
    private boolean enAire;
    private Marino piloto;
    private Marino copiloto;
    private String placa;
    
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
        if(enAire = false){
            bandera = u.seranDestruidas(longitud,latitud);
        }
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
        boolean bandera = false;
        if(piloto == null){
            bandera = true;
        }
        return bandera;
    }
    
    /**
     * Comprueba si el avion esta en el aire
     * @Return un boolean
     */
    public boolean estaAire(){
        return enAire;
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
     * Obtiene un piloto
     * @Return un pilot de clase marino
     */
    public Marino piloto(){
        return piloto;
    }
}
