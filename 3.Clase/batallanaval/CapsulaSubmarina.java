
/**
 * Write a description of class CapsulaSubmarina here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CapsulaSubmarina extends Maquina{
    private Maquina nodriza;
         
    /**
     * Consulta las maquinas debiles de una flota
     * Un barco es debil si tiene menos de 5 marinos
     * Un avion es debil si no tiene piloto principal
     * Un portaaviones es debil si es un barco debil o alguno de sus aviones en aire es debil
     * @Return un boolean si son debiles
     */
    public boolean maquinasDebiles(){
        return false;
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
        return false;
    }
    
    /**
     * Establece una maquina nodriza a cada capsula
     */
    public void setNodriza(Maquina m){
        Maquina a = m;    
        if(a.getClass() == Barco.class || a.getClass() == CapsulaSubmarina.class){
            nodriza = m;
        }
    }
             
    /**
     * Las maquinas estan preparadas para autodestruirse
     * Los barcos y aviones se auto destruyen si reciben la instruccion
     * Las capsulas se auto destruyen si su nodriza es destruida
     * @Param instruccion, dice si se destruyen o no
     */
    public void autoDestruirse(boolean instruccion){
        boolean bandera = nodriza.getAutoDes();
        if(bandera){
            autoDes = true;
            System.out.println("Se auto destruye ya que su nodriza fue destruida");
        }
    }
}


