package domain;


public abstract class Comodin extends Casilla {

    /**Constructor de la clase Comodin */
    public Comodin() {
    }

    /*Metodo que verifica el comportamiento de una casilla*/
    public abstract void getComportamiento(Casilla[][] tablero, Jugador jugador, daPOOs juego);

}
