package domain;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class daPOOs {

    private Tablero tablero;
    private Jugador enTurno;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private ArrayList<String> colorList = new ArrayList<String>();
    private int escogidaX;
    private int escogidaY;
    private boolean mover = true;
    private int posX;
    private int posY;

    public daPOOs(List<String> nombres, List<String> colores, List<String> comodines) {
        colorList.add("Black");
        colorList.add("White");
        if (nombres.size() == 2) {
            jugadores.add(new Jugador(nombres.get(0), colores.get(0), true, true, 1));
            jugadores.add(new Jugador(nombres.get(1), colores.get(1), true, false, 2));
        } else {
            jugadores.add(new Jugador(nombres.get(0), String.valueOf(colores.get(0)), true, true, 1));
            jugadores.add(new Maquina("Maquina", String.valueOf(colores.get(1)), false, false, 2));
        }
        enTurno = jugadores.get(0);
        tablero = new Tablero(jugadores, comodines);
    }

    public void juego(int x, int y) {
        String color = enTurno.getColor();
        Jugador enemigo = getEnemigo();
        if (enTurno.miFicha(x, y)) {
            escogidaX = x;
            escogidaY = y;
        }
        // revisar cuando hagamos comer escogida != x y escogaY != Y
        if (tablero.esCorrecta(x, y) && tablero.estaVacia(x, y) && (escogidaX != x && escogidaY != y)
                && puedeMoverFicha(x, y) && mover) {
            tablero.delFicha(escogidaX, escogidaY);
            enTurno.removeFicha(escogidaX, escogidaY);
            enTurno.addFicha(x, y, color);
            tablero.setFicha(x, y, color);
            CambiarTurno();
        }

        if (tablero.esCorrecta(x, y) && tablero.estaVacia(x, y) && (escogidaX != x &&
                escogidaY != y)
                && puedeComerFicha(x, y)) {
            tablero.delFicha(escogidaX, escogidaY);
            enTurno.removeFicha(escogidaX, escogidaY);
            enTurno.addFicha(x, y, color);
            tablero.setFicha(x, y, color);
            posX = getComidaPosX();
            posY = getComidaPosY();
            enemigo.removeFicha(posX, posY);
            //tablero.delFicha(posX, posY);
            if (volverAComer(x, y)) {
                mover = false;
            } else {
                mover = true;
                CambiarTurno();
            }
        }

    }

    private int parseInt(String string) {
        return 0;
    }

    public void CambiarTurno() {
        enTurno.setTurno(false);
        if (enTurno.equals(jugadores.get(jugadores.size() - 1))) {
            enTurno = jugadores.get(0);
        } else {
            enTurno = jugadores.get(jugadores.indexOf(enTurno) + 1);
        }
        enTurno.setTurno(true);
    }

    public boolean puedeMoverFicha(int x, int y) {
        boolean bandera = tablero.puedeMover(escogidaX, escogidaY, x, y);
        return bandera;
    }

    public boolean puedeComerFicha(int x, int y) {
        boolean bandera = tablero.puedeComer(escogidaX, escogidaY, x, y);
        return bandera;
    }

    public boolean volverAComer(int x, int y) {
        boolean bandera = tablero.volverAComer(x, y);
        return bandera;
    }

    public Jugador getJugadorTurno() {
        return enTurno;
    }

    public Tablero getTableroJuego() {
        return this.tablero;
    }

    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }

    public int getComidaPosX() {
        int posComidaX = tablero.getComidaPosX();
        return posComidaX;
    }

    public int getComidaPosY() {
        int posComidaY = tablero.getComidaPosY();
        return posComidaY;
    }

    public Jugador getEnemigo() {
        Jugador enemigo;
        if (enTurno.equals(jugadores.get(jugadores.size() - 1))) {
            enemigo = jugadores.get(0);
        } else {
            enemigo = jugadores.get(jugadores.indexOf(enTurno) + 1);
        }
        return enemigo;
    }
}
