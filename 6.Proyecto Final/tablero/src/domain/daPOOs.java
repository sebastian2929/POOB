package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal
 * 
 * @version 2.7
 */
public class daPOOs {

    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private ArrayList<String> colorList = new ArrayList<String>();
    private List<String> nombresJugadores = new ArrayList<String>();
    private Tablero tablero;
    private Jugador enTurno;
    private int escogidaX;
    private int escogidaY;
    private boolean mover = false;
    private boolean comer = false;
    private boolean gun = false;
    private Ficha enemigo = null;
    private Jugador winner = null;

    /**
     * Constructor de la clase daPOOs.
     * 
     * @param nombres   lista de nombres de los jugadores
     * @param colores   lista de Colores de cada jugador
     * @param comodines Lista de comodines que se van a usar.
     */
    public daPOOs(List<String> nombres, List<String> colores, List<String> casillas, int porcentaje) {
        colorList.add("Black");
        colorList.add("White");
        nombresJugadores = nombres;
        if (nombres.size() == 2) {
            jugadores.add(new Jugador(nombres.get(0), colores.get(0), true, true, 1));
            jugadores.add(new Jugador(nombres.get(1), colores.get(1), true, false, 2));
        } else {
            jugadores.add(new Jugador(nombres.get(0), String.valueOf(colores.get(0)), true, true, 1));
            jugadores.add(new Maquina("Maquina", String.valueOf(colores.get(1)), false, false, 2));
        }
        enTurno = jugadores.get(0);
        tablero = new Tablero(jugadores, casillas, porcentaje);
    }

    /**
     * Metodo que permite jugar.
     * 
     * @param x posicion x de la ficha seleccinada.
     * @param y posicion y de la ficha seleccinada.
     */

    public void juego(int x, int y) {
        Jugador enemigo = getEnemigo();
        Casilla casilla = tablero.getCasilla(x, y);
        if (enTurno.miFicha(casilla.getFicha()) && winner == null) {
            escogidaX = x;
            escogidaY = y;
            mover = puedeMover(escogidaX, escogidaY);
            comer = puedeComer(escogidaX, escogidaY);
        }
        if (tablero.esCorrecta(x, y) && tablero.estaVacia(x, y) && (escogidaX != x && escogidaY != y) && mover
                && moverFicha(x, y)) {
            Ficha ficha = podiaComer(x, y);
            tablero.cambiarPosicionFicha(escogidaX, escogidaY, x, y);
            enTurno.cambiarPosicionFicha(tablero.getFicha(escogidaX, escogidaY), tablero.getFicha(x, y));
            tablero.removeFicha(escogidaX, escogidaY);
            if (tablero.comodinActivo(x, y)) {
                tablero.getComportamiento(x, y, enTurno, this);
            }
            if (comer) {
                enTurno.removeFicha(tablero.getFicha(x, y));
                tablero.removeFicha(x, y);
            } else {
                if (ficha != null) {
                    enTurno.removeFicha(ficha);
                    tablero.removeFicha(ficha.getPosX(), ficha.getPosY());
                }
            }
            tablero.getComportamiento(x, y, enTurno, getEnemigo());
            coronar();
            ganar();
            CambiarTurno();
        }
        if (tablero.esCorrecta(x, y) && tablero.estaVacia(x, y) && (escogidaX != x && escogidaY != y) && comer
                && comerFicha(x, y)) {
            tablero.cambiarPosicionFicha(escogidaX, escogidaY, x, y);
            enTurno.cambiarPosicionFicha(tablero.getFicha(escogidaX, escogidaY), tablero.getFicha(x, y));
            tablero.removeFicha(escogidaX, escogidaY);
            enemigo.removeFicha(fichaComida());
            enTurno.setFichasCapturadas();
            if (tablero.comodinActivo(x, y)) {
                tablero.getComportamiento(x, y, enTurno, this);
            }
            tablero.getComportamiento(x, y, enTurno, getEnemigo());
            coronar();
            ganar();
            if (!puedeComer(x, y)) {
                CambiarTurno();
            }
        }
    }

    /**
     * Metodo que selecciona una ficha enemiga
     * 
     * @param fichaEnemiga ficha del contrario
     * @return Una ficha del contrario
     */
    public Ficha setEnemigo(Ficha fichaEnemiga) {
        return fichaEnemiga;
    }

    /**
     * Metodo que retorna una ficha del enemigo
     * 
     * @return ficha del contrario
     */
    public Ficha getFichaEnemigo() {
        return enemigo;
    }

    /**
     * Metodo que pone un comodin de tipo gun
     * 
     * @param bandera
     */
    public void setGun(Boolean bandera) {
        gun = bandera;
    }

    /**
     * Metodo que retorna si un comodin de tipo gun esta activo
     * 
     * @return si un comodin de tipo gun esta activo
     */
    public boolean getGun() {
        return gun;
    }

    /**
     * Metodo que selecciona una ficha enemiga
     * 
     * @param x posicion en X
     * @param y posicion en Y
     * @return las posiciones de la ficha enemiga
     */
    public int[] seleccionarFichaEnemiga(int x, int y) {
        int[] posiciones = new int[2];
        posiciones[0] = x;
        posiciones[1] = y;
        return posiciones;
    }

    /**
     * Metodo que cambia de turno.
     */
    public void CambiarTurno() {
        enTurno.setTurno(false);
        if (enTurno.equals(jugadores.get(jugadores.size() - 1))) {
            enTurno = jugadores.get(0);
        } else {
            enTurno = jugadores.get(jugadores.indexOf(enTurno) + 1);
        }
        enTurno.setTurno(true);
    }

    public void coronar() {
        tablero.coronar(enTurno);
    }

    /**
     * Abre el archivo donde esta el estado del juego
     * 
     * @param archivo Archivo para leer.
     * @return Juego guardado
     * @throws PoobchisException ARCHIVO_NO_ENCONTRADO En caso de que no se
     *                           encuentre el archivo en la raiz.
     */
    public static daPOOs abra(File archivo) throws daPOOsException {
        daPOOs juego = null;
        try {
            if (!archivo.exists()) {
                throw new daPOOsException(daPOOsException.ARCHIVO_NO_ENCONTRADO);
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo));
            juego = (daPOOs) in.readObject();
            in.close();
        } catch (Exception e) {
            throw new daPOOsException(daPOOsException.ARCHIVO_NO_ENCONTRADO);
        }
        return juego;
    }

    /**
     * Guarda el estado del juego
     * 
     * @param archivo Archivo donde se guardara
     * @throws PoobchisException ARCHIVO_NO_ENCONTRADO En caso de que no se
     *                           encuentre el archivo en la raiz.
     */
    public void guarde(File archivo) throws daPOOsException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            System.out.println("No se pudo guardar el archivo");
            e.printStackTrace();
        }
    }

    /**
     * Metodo que define si puede mover una ficha.
     * 
     * @param x posiscion x de la ficha seleccionada.
     * @param y posiscion y de la ficha seleccionada.
     * @return bansera, si puede mover la ficha;
     */
    public boolean puedeMover(int x, int y) {
        boolean bandera = tablero.puedeMover(x, y);
        return bandera;
    }

    /**
     * Metodo que define si puede comer una fciha.
     * 
     * @param escogidaX posicion de la ficha en las posicion x
     * @param escogidaY posicion de la ficha en la posicion y
     * @return bandera si puede volver a comer.
     */
    public boolean puedeComer(int x, int y) {
        boolean bandera = tablero.puedeComer(x, y);
        return bandera;
    }

    /**
     * Metodo que define si la ficha se movio.
     * 
     * @param x posición x de la ficha seleccionada.
     * @param y posiscion y de la ficha seleccionada.
     * @return bansera, si puede mover la ficha.
     */
    public boolean moverFicha(int x, int y) {
        boolean bandera = tablero.moverFicha(this.escogidaX, this.escogidaY, x, y);
        return bandera;
    }

    /**
     * Metodo que define si la ficha comio.
     * 
     * @param x posición x de la ficha seleccionada.
     * @param y posiscion y de la ficha seleccionada.
     * @return bansera, si puede mover la ficha.
     */
    public boolean comerFicha(int x, int y) {
        boolean bandera = tablero.comerFicha(this.escogidaX, this.escogidaY, x, y);
        return bandera;
    }

    /**
     * Metodo que retorna el juegador en turno.
     * 
     * @return Jugador en turno.
     */
    public Jugador getJugadorTurno() {
        return enTurno;
    }

    /**
     * Metodo que retirna el tablero de juego.
     * 
     * @return
     */
    public Tablero getTableroJuego() {
        return this.tablero;
    }

    /**
     * Metodo que devuleve el arraylist de Jugadores del juego.
     * 
     * @return jugadores.
     */
    public List<String> getJugadores() {
        return nombresJugadores;
    }

    /**
     * Metodo que retorna el ganador del juego;
     * 
     * @return
     */
    public Jugador getWinner() {
        return winner;
    }

    /**
     * Metodo que retorna la ficha que fue comida
     * 
     * @return una ficha comida
     */
    public Ficha fichaComida() {
        Ficha ficha = tablero.fichaComida();
        return ficha;
    }

    /**
     * Metodo que verifica si alguna ficha podia comer
     * 
     * @param x posicion en X
     * @param y posicion en Y
     * @return ficha que podia comer
     */
    public Ficha podiaComer(int x, int y) {
        ArrayList<Ficha> fichasAborrar = tablero.podiaComer(enTurno);
        Ficha ficha = null;
        for (Ficha fichaAborrar : fichasAborrar) {
            int posFichaX = fichaAborrar.getPosX();
            int posFichaY = fichaAborrar.getPosY();
            if (posFichaX != x || posFichaY != y) {
                ficha = fichaAborrar;
            }
        }
        return ficha;
    }

    /**
     * Metdodoq ue obtiene el jugador enemigo al jugador en turno.
     * 
     * @return Jugador enemigo.
     */
    public Jugador getEnemigo() {
        Jugador enemigo;
        if (enTurno.equals(jugadores.get(jugadores.size() - 1))) {
            enemigo = jugadores.get(0);
        } else {
            enemigo = jugadores.get(jugadores.indexOf(enTurno) + 1);
        }
        return enemigo;
    }

    /**
     * Metodo que obtiene la cantidad de comodines
     * 
     * @return cantoidad de comodines
     */
    public int getComodines() {
        int num = tablero.getComodines();
        return num;
    }

    /**
     * Metodo que verifica si un jugador gana el juego
     * 
     * @return si un jugador gana
     */
    public boolean ganar() {
        boolean bandera = false;
        Jugador jugadorEnemigo = getEnemigo();
        int fichasE = jugadorEnemigo.getFichas().size();
        if (fichasE == 0) {
            winner = enTurno;
            bandera = true;
        }
        return bandera;
    }
}
