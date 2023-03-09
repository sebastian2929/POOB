package domain;

import java.util.ArrayList;

public class Jugador {
    private int id;
    private String nombreJugador;
    private boolean enTurno;
    private String color;
    private int fichaCapturada;
    private int comodinesActivos;
    private boolean esHumano;
    private int fichasCambiadas;
    private ArrayList<Ficha> misFichas = new ArrayList<Ficha>();

    public Jugador(String nombre, String color, boolean humano, boolean turno, int id) {
        this.nombreJugador = nombre;
        this.color = color;
        this.esHumano = humano;
        this.enTurno = turno;
        this.id = id;
        this.fichaCapturada = 0;
        this.comodinesActivos = 0;
        this.fichasCambiadas = 0;
        Fichas(id);

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

    public boolean miFicha(int x, int y) {
        boolean bandera = false;
        int posX = 0;
        int posY = 0;
        for (Ficha ficha : misFichas) {
            posX = ficha.getPosX();
            posY = ficha.getPosY();
            if (posX == x && posY == y) {
                bandera = true;
            }
        }
        return bandera;
    }

    public ArrayList<Ficha> getFichas() {
        return misFichas;
    }

    public void removeFicha(int x, int y) {
        int posX = 0;
        int posY = 0;
        Ficha removeFicha = null;
        for (Ficha ficha : misFichas) {
            posX = ficha.getPosX();
            posY = ficha.getPosY();
            if (posX == x && posY == y) {
                removeFicha = ficha;
            }
        }
        misFichas.remove(removeFicha);
    }

    public void addFicha(int x, int y, String color) {
        Ficha ficha = new Ficha(x, y, color);
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
     * @return True si el jugador es humano,
     *         de lo contrario False es decir es maquina.
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

    private void Fichas(int id) {

        if (id == 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if ((i + j) % 2 == 1) {
                        Ficha ficha = new Ficha(i, j, "Black");
                        misFichas.add(ficha);
                    }
                }
            }
        }
        if (id == 2) {
            for (int i = 6; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if ((i + j) % 2 == 1) {
                        Ficha ficha = new Ficha(i, j, "White");
                        misFichas.add(ficha);
                    }
                }
            }
        }
    }
}
