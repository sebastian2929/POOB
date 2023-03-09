package domain;

import java.util.ArrayList;

/**
 * Clase que crea un jugador
 * 
 * @version 1.3
 */
public class Jugador {
    private int id;
    private String nombreJugador;
    private boolean enTurno;
    private String color;
    private int fichaCapturada;
    private boolean esHumano;
    private int fichasCambiadas;
    private ArrayList<Ficha> misFichas = new ArrayList<Ficha>();

    /**
     * Costructor de la clase Jugador.
     * 
     * @param nombre dombre del jugador.
     * @param color  color ue representa las fichas del jugaodor.
     * @param humano definir si es humano o maquina.
     * @param turno  definir su turno.
     * @param id     id que representa el jugador.
     */
    public Jugador(String nombre, String color, boolean humano, boolean turno, int id) {
        this.nombreJugador = nombre;
        this.color = color;
        this.esHumano = humano;
        this.enTurno = turno;
        this.id = id;
        this.fichaCapturada = 0;
        this.fichasCambiadas = 0;
        Fichas(id);

    }

    /**
    *Metodo que retorna las fichas coronadas por un jugador     
    * @return
    */
    public int getFichasCoronodas(){
        return fichasCambiadas;
    }

    /**
     * Metodo que suma uno cada vez que se corona una ficha
     * @return
     */
    public void setFichasCoradas(){
        fichasCambiadas += 1;
    }

    /**
     * Metodo que rotorna las fichas capturadas por un jugados
     * @return
     */
    public int getFichasCapturadas(){
        return fichaCapturada;
    }

    /**
     * Metodo que suma uno cada vez que se captura una ficha
     * @return
     */
    public void setFichasCapturadas(){
            fichaCapturada += 1;
    }

    /**
     * Verifica si un jugador esta en turno.
     * 
     * @return True si esta en turno, de lo contrario False.
     */
    public boolean getTurno() {
        return this.enTurno;
    }

    /**
     * Actualiza el turno del jugador.
     * 
     * @param turno Nuevo estado del turno.
     */
    public void setTurno(boolean turno) {
        this.enTurno = turno;
    }

    /**
     * Obtiene el nombre del jugador
     * 
     * @return Cadena con el nombre del jugador
     */
    public String getName() {
        return this.nombreJugador;
    }

    /**
     * Obtiene el color del jugador.
     * 
     * @return Color que identifica al jugador.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Verifica si es ficha del jugador.
     * 
     * @param ficha ficha que se va a verificar .
     * @return bandera si es o no micha del jugador.
     */
    public boolean miFicha(Ficha ficha) {
        boolean bandera = false;
        if (misFichas.contains(ficha)) {
            bandera = true;
        }
        return bandera;
    }

    /**
     * Da el arreglo de fichas del jugador.
     * 
     * @return fichas del jugador.
     */
    public ArrayList<Ficha> getFichas() {
        return misFichas;
    }

    /**
     * Metodo que cambia de posicion una ficha del jugador.
     * 
     * @param fichaVieja ficha desactualizada.
     * @param fichaNueva ficha actualizada.
     */
    public void cambiarPosicionFicha(Ficha fichaVieja, Ficha fichaNueva) {
        misFichas.remove(fichaVieja);
        misFichas.add(fichaNueva);
    }

    /**
     * Metodo que borra la ficha del arrayList del jugador.
     * 
     * @param ficha ficha a remover.
     */
    public void removeFicha(Ficha ficha) {
        misFichas.remove(ficha);
    }

    /**
     * Metodo que annade una ficha al arrayList del jugador.
     * 
     * @param ficha ficha a annadir.
     */
    public void addFicha(Ficha ficha) {
        misFichas.add(ficha);
    }

    /**
     * Metodo que determina las ficas del jugador.
     * 
     * @param ArrayList<String> , fichas del jugador.
     */

    public void setFichas(ArrayList<Ficha> fichas) {
        misFichas = fichas;
    }

    /**
     * Verifica si un jugador esta en turno.
     * 
     * @return True si esta en turno, de lo contrario False.
     */
    public boolean estaEnTurno() {
        return this.enTurno;
    }

    /**
     * Verifica si el jugador es humano o no.
     * 
     * @return True si el jugador es humano,de lo contrario False es decir es
     *         maquina.
     */
    public boolean esHumano() {
        return this.esHumano;
    }

    /**
     * Obtiene el identificador del jugador
     * 
     * @return Entero que indica el numero de jugador en el tablero.
     */
    public int getId() {
        return this.id;
    }

    public ArrayList<Ficha> podiaComer(Casilla[][] tablero) {
        ArrayList<Ficha> fichasAborrar = new ArrayList<Ficha>();
        for (Ficha ficha : misFichas) {
            if (ficha.puedeComer(ficha.getPosX(), ficha.getPosY(), tablero, color)) {
                fichasAborrar.add(ficha);
            }
        }
        return fichasAborrar;
    }

    /**
     * Definir las fichas de cada jugador.
     * 
     * @param id id del jugador.
     */
    private void Fichas(int id) {
        if (id == 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if ((i + j) % 2 == 1) {
                        Ficha ficha = new Peon(i, j, "Black");
                        misFichas.add(ficha);
                    }
                }
            }
        }
        if (id == 2) {
            for (int i = 6; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if ((i + j) % 2 == 1) {
                        Ficha ficha = new Peon(i, j, "White");
                        misFichas.add(ficha);
                    }
                }
            }
        }
    }
}
