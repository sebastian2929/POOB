package domain;

import java.util.ArrayList;

/**
 * Clase donde se implementa el juego Tant Fant
 * 
 * @Autor Andres Arias - Sebastian Blanco
 * @Version 13-11-22
 */
public class TantFant {

    private Tablero tablero;
    private Jugador jugador1;
    private ArrayList<String> fichasJuno;
    private ArrayList<String> fichasJdos;
    private Jugador jugador2;
    private int winner;
    private int escogidaX;
    private int escogidaY;

    public TantFant() {
        this.tablero = new Tablero();
        this.jugador1 = new Jugador(1, true);
        fichasJuno = fichasJ1();
        this.jugador1.setFichas(fichasJuno);
        this.jugador1.setColor("black");
        this.jugador2 = new Jugador(2, false);
        fichasJdos = fichasJ2();
        this.jugador2.setFichas(fichasJdos);
        this.jugador2.setColor("blue");
        winner = 0;
    }

    /**
     * 
     * @param jugador
     * @param posX
     * @param posY
     * @param x
     * @param y
     */
    public void juego(Jugador jugador, int posX, int posY) {
        if (jugador.miFicha(posX, posY)) {
            escogidaX = posX;
            escogidaY = posY;
        }
        if (tablero.verifica(posX, posY, escogidaX, escogidaY) && (escogidaX != posX || escogidaY != posY)) {
            jugador.removeFicha(escogidaX, escogidaY);
            tablero.dellFicha(escogidaX, escogidaY);
            int idJugador = jugador.getIdJugador();
            jugador.addFicha(posX, posY);
            tablero.setFicha(posX, posY, idJugador);
            cambiarTurno();
        }
    }

    /**
     * Obtiene el jugador que esta actualmente en turno
     * 
     * @return Jugador que esta actualmente en turno.
     */
    public Jugador getJugadorEnTurno() {
        Jugador enTurno;
        if (jugador1.getTurno()) {
            enTurno = jugador1;
        } else {
            enTurno = jugador2;
        }
        return enTurno;
    }

    /**
     * Cambia el turno al jugador contario
     */
    public void cambiarTurno() {
        if (jugador1.getTurno()) {
            jugador1.setTurno(false);
            jugador2.setTurno(true);
        } else {
            jugador2.setTurno(false);
            jugador1.setTurno(true);
        }
    }

    public boolean ganador() {
        Jugador jugador = jugador1;
        boolean bandera = false;
        boolean enturno = jugador1.getTurno();
        if (!enturno) {
            jugador = jugador2;
        }
        if (horizontales(jugador) || diagolanes(jugador) || verticales(jugador)) {
            bandera = true;
        }
        return bandera;
    }

    /**
     * Obtiene el jugador dependiendo de su id 1 para jugador1 2 para jugador2.
     * 
     * @param idJugador Id del jugador que se quiere obtener.
     * @return Jugador que se obtiene dado el id del mismo.
     */
    public Jugador getJugador(int idJugador) {
        return (idJugador == 1 ? this.jugador1 : this.jugador2);
    }

    /**
     * Obtiene le tablero de juego.
     * 
     * @return Tablero con las casas para realizar acciones sobre ellas.
     */
    public Tablero getTablero() {
        return this.tablero;
    }

    /*
     * metodo que determina las fichas del jugador 1;
     */

    private ArrayList<String> fichasJ1() {
        ArrayList<String> fichasUno = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            tablero.setFicha(0, i, 1);
            String numero = String.valueOf(i);
            fichasUno.add("0" + "," + numero);
        }
        return fichasUno;
    }

    /*
     * metodo que determina las fichas del jugador 2;
     */

    private ArrayList<String> fichasJ2() {
        ArrayList<String> fichasDos = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            tablero.setFicha(2, i, 2);
            String numero = String.valueOf(i);
            fichasDos.add("2" + "," + numero);
        }
        return fichasDos;
    }

    /*
     * verifica si el jugador1 gano
     */

    private boolean horizontales(Jugador jugador) {
        boolean bandera = false;
        Ficha[][] tableroGanador = tablero.getFichasTablero();
        String color = jugador.getColor();
        for (int i = 0; i < 3; i++) {
            if (tableroGanador[i][0] != null && tableroGanador[i][1] != null && tableroGanador[i][2] != null) {
                String ficha = tableroGanador[i][0].getColor();
                String ficha2 = tableroGanador[i][1].getColor();
                String ficha3 = tableroGanador[i][2].getColor();
                if (ficha.equals(color) && ficha2.equals(color) && ficha3.equals(color)) {
                    if (jugador.getIdJugador() == 1 && i != 0) {
                        bandera = true;
                    }
                    if (jugador.getIdJugador() == 2 && i != 2) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    private boolean verticales(Jugador jugador) {
        boolean bandera = false;
        Ficha[][] tableroGanador = tablero.getFichasTablero();
        String color = jugador.getColor();
        for (int i = 0; i < 3; i++) {
            if (tableroGanador[0][i] != null && tableroGanador[1][i] != null && tableroGanador[2][i] != null) {
                String ficha = tableroGanador[0][i].getColor();
                String ficha2 = tableroGanador[1][i].getColor();
                String ficha3 = tableroGanador[2][i].getColor();
                if (ficha.equals(color) && ficha2.equals(color) && ficha3.equals(color)) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    private boolean diagolanes(Jugador jugador) {
        boolean bandera = false;
        Ficha[][] tableroGanador = tablero.getFichasTablero();
        if (tableroGanador[0][0] != null && tableroGanador[1][1] != null && tableroGanador[2][2] != null
                && tableroGanador[0][2] != null && tableroGanador[2][0] != null) {
            String color = jugador.getColor();
            String ficha = tableroGanador[0][0].getColor();
            String ficha2 = tableroGanador[1][1].getColor();
            String ficha3 = tableroGanador[2][2].getColor();
            String ficha4 = tableroGanador[0][2].getColor();
            String ficha5 = tableroGanador[2][0].getColor();
            if (ficha.equals(color) && ficha2.equals(color) && ficha3.equals(color)) {
                bandera = true;
            }
            if (ficha4.equals(color) && ficha2.equals(color) && ficha5.equals(color)) {
                bandera = true;
            }
        }
        return bandera;
    }
}
