
/**
 * Write a description of class Marino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Marino
{
    private String nombre;
    private int rango;
    private boolean autoDes = false;
    
    /**
     * Las maquinas estan preparadas para autodestruirse
     * Los barcos y aviones se auto destruyen si reciben la instruccion
     * Las capsulas se auto destruyen si su nodriza es destruida
     * @Param instruccion, dice si se destruyen o no
     */
    public void autoDestruirse(boolean instruccion){
        if(instruccion){
            autoDes = true;
            System.out.println("Se auto destruye el marino");
        }
    }
}
