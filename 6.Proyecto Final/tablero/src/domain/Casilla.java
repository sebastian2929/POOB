package domain;

/**
 * Clase que crea las casillas
 * 
 * @version 1.7
 */
public abstract class Casilla {

    protected int posX;
    protected int posY;
    protected String tipo;
    protected Ficha ficha = null;
    protected Ficha fichaComida = null;
    protected Comodin comodin = null;
    protected String color;

    /**
     * Construcctor de la clase casilla
     * 
     * @param posX           posicion en X
     * @param posY           posicion en Y
     * @param listaComodines Lista de los comodines que el usuario escoge
     */
    public Casilla(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /* Constructor de la clase casilla */
    public Casilla() {
    }

    /**
     * Metodo que retorna la posicion X de la casilla
     * 
     * @return una posicion en y
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * Metodo que retorna la posicion y de la casilla
     * 
     * @return una posicion en y
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * Metodo que retorna el tipo de casilla
     * 
     * @return el tipo de una casilla
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Metodo que retorna una ficha ubicada en la casilla
     * 
     * @return una ficha
     */
    public Ficha getFicha() {
        return this.ficha;
    }

    /**
     * Metodo que retorna el color de la ficha que se encuenttra en la casilla
     * 
     * @return un color de una ficha
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Metodo que agrega una ficha en la casilla
     * 
     * @param ficha
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    /**
     * metodo que retorna un comodin que exista en la casilla
     * 
     * @return un comodin
     */
    public Comodin getComodin() {
        return this.comodin;
    }

    /**
     * Metodo que le pone un tipo a la casilla
     * 
     * @param tipo Tipo de una casilla
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que remueve una ficha de la casilla
     */
    public void removeFicha() {
        this.ficha = null;
    }

    /**
     * Metodo que verifica si una casilla no tiene ninguna ficha
     * 
     * @return si existe una ficha en la casilla
     */
    public boolean estaVacia() {
        boolean bandera = false;
        if (ficha == null) {
            bandera = true;
        }
        return bandera;
    }

    /**
     * Metodo que pone una ficha en la casilla
     * 
     * @param ficha ficha que va a estar en una casilla
     */
    public void setFichaComida(Ficha ficha) {
        fichaComida = ficha;
    }

    /**
     * Metodo que retorna la ficha que fue comida
     * 
     * @return ficha comida
     */
    public Ficha getFichaComida() {
        return fichaComida;
    }

    /**
     * Metodo que verifica si un comodin existe en una casilla
     * 
     * @return si existe un comodin
     */
    public boolean comodinActivo() {
        boolean bandera = false;
        if (this.ficha != null && comodin != null) {
            bandera = true;
        }
        return bandera;
    }

    /* Metodo que verifica el comportamiento de una casilla */
    public abstract void getComportamiento(Casilla[][] tablero, Jugador jugador, Jugador enemigo);

    /* Metodo que verifica el comportamiento de una casilla */
    public abstract void getComportamiento(Casilla[][] tablero, Jugador jugador, daPOOs juego);

}
