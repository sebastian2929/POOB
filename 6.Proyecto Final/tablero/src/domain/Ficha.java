package domain;

/**
 * Clase que crea las fichas
 * 
 * @version 1.3
 */
public abstract class Ficha {
    protected String color;
    protected String tipo;
    protected int posX;
    protected int posY;

    /**
     * Constructor de la clase ficha
     * @param x posicion en X
     * @param y posicion en Y
     * @param color Color de las fichas del jugador
     */
    public Ficha(int x, int y, String color) {
        this.color = color;
        posX = x;
        posY = y;
    }

    /**
     * Metodoq ue retorna el color de las ficha del jugador en turno
     * @return color de las fichas del jugador
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Metodo que retrona el tipo de ficha 
     * @return tipo de ficha
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Metodo que retorna la posicion X de una ficha
     * @return las poscion en X de una ficha
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Metodo que retorna la posicion Y de una ficha
     * @return la posicion Y de una ficha
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Metodo que agrega una ficha en la posicion X
     * @param x posicion X de la ficha
     */
    public void setPosX(int x) {
        this.posX = x;
    }

    /**
     * Metodo que agrega una ficha en la posicion Y
     * @param y posicion Y de la ficha
     */
    public void setPosY(int y) {
        this.posY = y;
    }

    /**
     * Metodo que verifica si se puede comer una ficha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Todas las casillas del juego
     * @param color Color de la ficha 
     * @return una verificacion si puede comer
     */
    public abstract boolean puedeComer(int escogidaX, int escogidaY, Casilla[][] tablero, String color);

    /**
     * Metodo que verifica si una ficha puede mover
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Todas las casillas del juego
     * @param color Color de las fichas
     * @return una verificacion si el jugador puede mover
     */
    public abstract boolean puedeMover(int escogidaX, int escogidaY, Casilla[][] tablero, String color);

    /**
     * Metodo que verifica si el jugador movio la ficha correctamente
     * @param escogidaX posicion X de la ficha
     * @param escogidaY posicion Y de la ficha
     * @param x posicion X a la que se va a mover
     * @param y posicion Y a la que se va a mover
     * @param tablero Casillas del juego
     * @param color Color de la ficha
     * @return Una verificacion de si el jugador movio
     */
    public abstract boolean moverFicha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color);

    /**
     * Metodo que verifica si se como una ficha
     * @param escogidaX posicion X de la ficha
     * @param escogidaY posicion Y de la ficha
     * @param x posicion X a la que se va a mover
     * @param y posicion Y a la que se va a mover
     * @param tablero Casillas del juego
     * @param color Color de la ficha
     * @return Una verificacion si el jugador comio una ficha 
     */
    public abstract boolean comerFicha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color);

}
