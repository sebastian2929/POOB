package domain;

/**
 * Jugador para el juego Tant Fant.
 * 
 * @Autor Andres Arias - Sebastian Blanco
 * @Version 13-11-22
 */
public class Tablero {
    private Ficha[][] tablero;

    /**
     * Constructor de la clase Tablero
     */
    public Tablero() {
        tablero = new Ficha[3][3];
    }

    /**
     * Metodo que borra la posicion de una ficha.
     * 
     * @param x posicion en X
     * @param y posicion en Y
     */
    public void dellFicha(int x, int y) {
        Ficha ficha = tablero[x][y];
        if (ficha != null) {
            tablero[x][y] = null;
        }

    }

    /**
     * Metodo que verifica si la posicion en el tablero esta vacia.
     * 
     * @param x posicion en X
     * @param y posicion en Y
     * @return boolean, que verifica si la posicion en el tablero esta vacia
     */
    public boolean estaVacia(int x, int y) {
        boolean var = false;
        Ficha ficha = tablero[x][y];
        if (ficha == null) {
            var = true;
        }
        return var;
    }

    /**
     * Metodo que ubica una ficha en una posicion.
     * 
     * @param posX posicion en x
     * @param posY posicion en y
     */
    public void setFicha(int posX, int posY, int id) {
        Ficha ficha = tablero[posX][posY];
        if (ficha == null) {
            Ficha newFicha = new Ficha(posX, posY, id);
            tablero[posX][posY] = newFicha;
        }
    }

    /**
     * metodo que limpia el tablero y reestablece el juego
     */
    public void limpiar() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (i == 0) {
                    Ficha ficha = tablero[i][j];
                    ficha.setColor("black");
                }
                if (i == 2) {
                    Ficha ficha = tablero[i][j];
                    ficha.setColor("blue");
                }
            }
        }
    }

    /**
     * Metodo que retorna el tablero
     * 
     * @return tablero
     */
    public Ficha[][] getFichasTablero() {
        return this.tablero;
    }

    /**
     * Metodo que verifica si se puede ubicar en una posicion segun el movimineto
     * 
     * @param x         posicion nueva en x
     * @param y         posicion nueva en y
     * @param escogidaX posicion guardada en X
     * @param escogidaY posicion guardada en Y
     * @return boolena
     */
    public boolean verifica(int x, int y, int escogidaX, int escogidaY) {
        boolean bandera = false;
        int distanciaX = 0;
        int distanciaY = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estaVacia(x, y) && i != escogidaX && j != escogidaY) {
                    distanciaX = Math.abs(escogidaX - x);
                    distanciaY = Math.abs(escogidaY - y);
                    if (distanciaX + distanciaY <= 2) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }
}
