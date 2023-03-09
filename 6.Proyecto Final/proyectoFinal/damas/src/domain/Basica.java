package domain;

/**
 * Clase que crea una casilla normal
 * 
 * @version 1.3
 */
public class Basica extends Casilla {

    /**
     * Constructor de la clase basica la cual es una casilla
     * 
     * @param posX
     * @param posY
     * @param listaComodines
     */
    public Basica(int posX, int posY) {
        super(posX, posY);
        tipo = "Basica";
    }

    /* Metodo que verifica el comportamiento de una casilla */
    public void getComportamiento(Casilla[][] tablero, Jugador jugador, Jugador enemigo) {
    }

    /* Metodo que verifica el comportamiento de una casilla */
    public void getComportamiento(Casilla[][] tablero, Jugador jugador, daPOOs juego) {
    }
}
