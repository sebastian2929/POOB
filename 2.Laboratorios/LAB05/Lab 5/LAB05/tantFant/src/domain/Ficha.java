package domain;

/**
 * Jugador para el juego Tant Fant.
 * 
 * @Autor Andres Arias - Sebastian Blanco
 * @Version 13-11-22
 */
public class Ficha {
    private int posX;
    private int posY;
    private String color = null;

    /**
     * Constructor de la clase ficha
     * 
     * @param posX la posicion en x de una ficha
     * @param Posy la posicion en y de una ficha
     */
    public Ficha(int posX, int posY, int id) {
        this.posX = posX;
        this.posY = posY;
        if (id == 1) {
            setColor("black");
        } else if (id == 2) {
            setColor("blue");
        }

    }

    /**
     * Metodo que obtine la posicion X de una ficha
     * 
     * @return la posicion X de una ficha
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * Metodo que obtine la posicion Y de una ficha
     * 
     * @return la posicion Y de una ficha
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * Metodo que modifica la posicion X de una ficha
     * 
     * @param x posicion X
     */
    public void setPosX(int x) {
        this.posX = x;
    }

    /**
     * Metodo que modifica la posicion Y de una ficha
     * 
     * @param y posicion Y
     */
    public void setPosY(int y) {
        this.posY = y;
    }

    /**
     * Metodo que retorna el color de una ficha
     * 
     * @return String, color de la ficha
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Metodo que cambia el color de una ficha
     * 
     * @param color color al que se va a cambiar la ficha
     */
    public void setColor(String color) {
        this.color = color;
    }
}
