import java.util.ArrayList;

/**
 * Write a description of class PortaAviones here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PortaAviones extends Barco
{
    ArrayList<Avion> aviones = new ArrayList<Avion>();
    private int capacidad;
    
    /**
     * Consulta las maquinas debiles de una flota
     * Un barco es debil si tiene menos de 5 marinos
     * Un avion es debil si no tiene piloto principal
     * Un portaaviones es debil si es un barco debil o alguno de sus aviones en aire es debil
     * @Return un boolean si son debiles
     */
    public boolean maquinasDebiles(){
        boolean bandera1 = false;
        boolean bandera2 = false;
        for(Avion a: aviones){
            if(a.estaAire()){
                bandera1 = a.maquinasDebiles();
            }
        }
        if(super.maquinasDebiles() || bandera1){
            bandera2 = true;        
        }
        return bandera2;
    }
    
    /**
     * Obtiene los pilotos que son marinos
     * @Return arreglo de marinos
     */
    public ArrayList<Marino> pilotos(){
        ArrayList<Marino> pil = new ArrayList<Marino>();
        for(Avion a: aviones){
            if(a.piloto() != null){
                pil.add(a.piloto());
            }
        }
        return pil;
    }
}
