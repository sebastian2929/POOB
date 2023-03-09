import java.util.ArrayList;

/**
 * Write a description of class Tablero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tablero
{
    private ArrayList<Flota> flotas = new ArrayList<Flota>();
    
    /**
     * Verifca si una ubicacion de ataque es adecuada (Verifica que no sean propias)
     * Destruye elementos enemigos sin ocacionar bajas propias
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return un boolean si fue un buen o mal ataque 
     */
    public boolean esBuenAtaque(int longitud, int latitud, String nombre){
        boolean bandera = false;
        int i = 0;
        while(!bandera && i<flotas.size()){
            if(!(flotas.get(i).getNombre() == nombre)){
                bandera = flotas.get(i).comprobar(longitud, latitud);
            }
            i++;
        }
        return bandera;
    }
    
    /**
     * Consulta el numero de flotas que lograron un moviemiento completo
     * @Return la cantidad de flotas que lograron un movimiento completo
     */
    public int alNorte(){
        int i = 0;
        for(Flota f: flotas){
            try
            {
                f.alNorte();
                i++;
            }
            catch (BatallaNavalExcepcion bne)
            {
                System.out.println(bne.getMessage());
                continue;
            }
        }
        return i;
    }
    
    /**
     * Consulta las flotas que tienen pilotos inflitrados
     * @Return un arreglo de flotas con pilotos infiltrados
     */
    public ArrayList<Flota> infiltrados() throws BatallaNavalExcepcion{
        ArrayList<Marino> pilotos = new ArrayList<Marino>();
        ArrayList<Marino> marinos = new ArrayList<Marino>();
        ArrayList<Flota> infiltrados = new ArrayList<Flota>();
        for(Flota f: flotas){
            boolean bandera = false;
            pilotos = f.getPilotos();
            marinos = f.getMarinos();
            if(marinos != null){
                for(Marino p: pilotos){
                    if(!marinos.contains(p)){
                        bandera = true;
                    }
                }
                if(bandera){
                    infiltrados.add(f);
                }
            } else {
                throw new BatallaNavalExcepcion(BatallaNavalExcepcion.infiltradosE);
            }
        }
        return infiltrados;
    }
    
    /**
     * Consulta la potencia que tiene el tablero 
     * La potencia es la suma de la potencia de las flotas
     * @Return la potencia del tablero
     */
    public int potencia() throws BatallaNavalExcepcion{
        int i = 0;
        int j = 0;
        for(Flota f: flotas){
            try{
                int pot = f.potencia();
                i = i + pot;
            }
            catch (BatallaNavalExcepcion bne){
                j++;
                continue;
            }
        }
        if(j>flotas.size()){
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.potenciaE2);
        }
        return i;
    }
}
