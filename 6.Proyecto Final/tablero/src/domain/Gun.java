package domain;

/**
 * Clase que crea un comodin de tipo gun
 * 
 * @version 0.2
 */
public class Gun extends Comodin {

    /**Constructor de la clase gun */
    public Gun() {
    }

    /**Metodo que realiza el comportamiento del comodin */
    public void getComportamiento(Casilla[][] tablero, Jugador jugador, daPOOs Juego) {
        Juego.setGun(true);
        Jugador enemigo = Juego.getEnemigo();
        Ficha ficha = Juego.getFichaEnemigo();
        enemigo.removeFicha(ficha);
        int x = ficha.getPosX();
        int y = ficha.getPosY();
        Casilla casilla = tablero[x][y];
        casilla.removeFicha();
        Juego.setGun(false);
    }

    /**Metodo que realiza el comportamiento del comodin */
    public void getComportamiento(Casilla[][] tablero, Jugador jugador, Jugador enemigo) {

    }
}
