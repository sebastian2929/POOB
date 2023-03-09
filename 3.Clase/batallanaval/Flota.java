import java.util.ArrayList;

/**
 * Write a description of class Flota here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flota
{
    private ArrayList<Maquina> maquinas = new ArrayList<Maquina>();
    private ArrayList<Maquina> noDebil = new ArrayList<Maquina>();
    private ArrayList<Marino> marinos = new ArrayList<Marino>();
    private ArrayList<Maquina> autoDestruidos = new ArrayList<Maquina>();
    private ArrayList<Marino> suicidoMarinos = new ArrayList<Marino>();
    private Tablero tablero;
    protected String nombre;
    
    /**
     * Mueve la flota una posicion al norte
     * En el momento que una maquina no se puede mover se para el moviento de toda la flota
     */
    public void alNorte() throws BatallaNavalExcepcion{
        for(Maquina m: maquinas){
            if(m.getLongitud() < 180){
                m.alNorte();
            } else{
                throw new BatallaNavalExcepcion(BatallaNavalExcepcion.alNorteE);
            }
        }
    }

    /**
     * Mueve todas las maquinas la distancia definida
     * El mundo tablero es circular
     * @Param entero deLon, la longitud de la maquina 
     * @Param entero deLat, la latitud de la maquina 
     */
    public void avance(int deLon, int deLat){
        for(Maquina m: maquinas){
            m.avance(deLon,deLat);
        }
    }

    /**
     * Consulta las maquinas que pueden afectarse por una explosion en la posicion dada
     * En una coordenada pueden estar muchas maquinas.
     * Los aviones en Aire no se destruyen
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return un arreglo de maquinas que seran destruidas
     */
    public ArrayList<Maquina> seranDestruidas(int longitud, int latitud){
        ArrayList<Maquina> seranDes = new ArrayList<Maquina>();
        boolean bandera = false;
        for(Maquina m: maquinas){
            bandera = m.seranDestruidas(longitud,latitud);
            if(bandera){
                seranDes.add(m);
            }
        }
        return seranDes;
    }

    /**
     * Consulta las maquinas debiles de una flota
     * Un barco es debil si tiene menos de 5 marinos
     * Un avion es debil si no tiene piloto principal
     * Un portaaviones es debil si es un barco debil o alguno de sus aviones en aire es debil
     * @Return un arreglo de maquinas que son debiles
     */
    public ArrayList<Maquina> maquinasDebiles(){
        ArrayList<Maquina> maquinaDeb = new ArrayList<Maquina>();
        boolean bandera = false;
        for(Maquina m: maquinas){
            bandera = m.maquinasDebiles();
            if(bandera){
                maquinaDeb.add(m);
            }
            else{ noDebil.add(m); }
        }
        return maquinaDeb;
    }

    /**
     * Verifca si una ubicacion de ataque es adecuada (Verifica que no sean propias)
     * Destruye elementos enemigos sin ocacionar bajas propias
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return un boolean que comprueba si es un buen ataque
     */
    public boolean esBuenAtaque(int longitud, int latitud){
        boolean bandera = false;
        bandera = tablero.esBuenAtaque(longitud,latitud,nombre);
        return bandera;
    }

    /**
     * Mueve todas las maquinas que nos son debiles paso a paso hacia la posicion a atacar indicada por (Lon, Lat)
     * @Param entero Lon, la longitud de la maquina 
     * @Param entero Lat, la latitud de la maquina 
     */
    public void ataquen(int lon, int lat){
        for(Maquina m: noDebil){
            m.ataquen(lon,lat);
        }
    }

    /**
     * Devuelve el nombre de la flota
     * @Return el nombre de la flota
     */
    public String getNombre(){
        return nombre;
    }

    
    /**
     * Comprueba si una maquina esta en la posicion dada
     * @Param entero longitud, la longitud de la maquina 
     * @Param entero latitud, la latitud de la maquina 
     * @Return un boolean comprobando si esta o no en la posicion
     */
    public boolean comprobar(int longitud, int latitud){
        boolean bandera = false;
        int i = 0;
        while(!bandera && i<maquinas.size()){
            bandera = maquinas.get(i).comprobar(longitud, latitud);
            i++;
        }
        return bandera;
    }

    /**
     * Consulta los pilotos de flota
     * @Return los pilotos asignados a los aviones de las flotas
     */
    public ArrayList<Marino> pilotos() throws BatallaNavalExcepcion{
        ArrayList<Marino> pilotos = new ArrayList<Marino>();
        ArrayList<Marino> marinosPorta = new ArrayList<Marino>();
        for(Maquina m: maquinas){
            if(m instanceof PortaAviones){
                marinosPorta = m.getMarinos();
                pilotos = m.pilotos();
            }
            for(Marino piloto: pilotos){
                if(!marinos.contains(piloto)){
                    throw new BatallaNavalExcepcion(BatallaNavalExcepcion.pilotosE1);
                }
                if(!marinosPorta.contains(piloto)){
                    throw new BatallaNavalExcepcion(BatallaNavalExcepcion.pilotosE2);
                }
                for(Marino pilot: pilotos){
                    int cont = 0;
                    if(pilot.equals(piloto)){
                        cont ++;
                        if(cont > 1){
                            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.pilotosE3);
                        }
                    }
                }
            }
        }
        return pilotos;
    }

    /**
     * Consulta la potencia de la flota
     * La potencia es el numero de maquinas no debiles
     * @Return la potencia de las flotas
     */
    public int potencia() throws BatallaNavalExcepcion{
        int potencia = noDebil.size();
        if(marinos.size() < maquinas.size()){
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.potenciaE);
        }
        return potencia;
    }

    /**
     * Devuelve los pilotos de una flota
     * @Return un arreglo de pilotos
     */
    public ArrayList<Marino> getPilotos(){
        ArrayList<Marino> pilotos = new ArrayList<Marino>();
        ArrayList<Marino> marinosPorta = new ArrayList<Marino>();
        for(Maquina m: maquinas){
            if(m instanceof PortaAviones){
                marinosPorta = m.getMarinos();
                pilotos = m.pilotos();
            }
        }
        return pilotos;
    }
    
    /**
     * Devuelve todos los marinos
     * @Return un arreglo de marinos
     */
    public ArrayList<Marino> getMarinos(){
        return marinos;
    }
}

