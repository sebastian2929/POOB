package domain;

/**
 * Clase que crea una casilla de tipo mine
 * 
 * @version 0.3
 */
public class Mine extends Casilla {
    /**
     * Constructor de la clase mine
     * 
     * @param posX           posicion X
     * @param posY           posicion Y
     * @param listaComodines lista de los comodines seleccionados por el jugador
     */
    public Mine(int posX, int posY) {
        super(posX, posY);
        tipo = "Mine";
    }

    /** Metodo que realiza el comportamiento de la clase mina */
    public void getComportamiento(Casilla[][] tablero, Jugador jugador, Jugador Jenemigo) {
        Jugador enemigo = Jenemigo;
        Jugador enTurno = jugador;
        Eliminar(enemigo, enTurno, tablero);
    }

    /** Metodo que realiza el comportamiento de la clase mina */
    public void getComportamiento(Casilla[][] tablero, Jugador jugador, daPOOs juego) {
    }

    /*
     * Metodo que elimina las fichas del area de la mina
     * 
     * @param enemigo
     * 
     * @param enTurno
     * 
     * @param tablero
     */
    private void Eliminar(Jugador enemigo, Jugador enTurno, Casilla[][] tablero) {
        int maxX = posX + 2;
        int maxY = posY + 2;
        int x = posX - 2;
        int y = posY - 2;
        String colorJugador = enTurno.getColor();
        for (int i = x; i <= maxX; i++) {
            for (int j = y; j <= maxY; j++) {
                if ((i + j) % 2 == 1 && i >= 0 && i < 10 && j >= 0 && j < 10) {
                    Casilla casilla = tablero[i][j];
                    if (!casilla.estaVacia()) {
                        Ficha fichaE = tablero[i][j].getFicha();
                        if (colorJugador.equals(fichaE.getColor())) {
                            enTurno.removeFicha(fichaE);
                            casilla.removeFicha();
                        } else {
                            enemigo.removeFicha(fichaE);
                            casilla.removeFicha();
                        }
                    }
                }
            }
        }
    }
}
