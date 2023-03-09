package domain;

import java.util.List;
import java.lang.reflect.InvocationTargetException;

public class Casilla {

    protected int posX;
    protected int posY;
    protected String tipo;
    protected Ficha ficha = null;
    protected Comodin comodin = null;
    protected String color;

    public Casilla(int posX, int posY, String tipo) {
        this.posX = posX;
        this.posY = posY;
        this.tipo = tipo;
        color = "Red";
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Ficha getFicha() {
        return this.ficha;
    }

    public String getColor() {
        return this.color;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Comodin getComodin() {
        return this.comodin;
    }

    public void setComodin(List<String> listaComodines) {
        int random = (int) (Math.random() * listaComodines.size());
        Comodin nuevoComodin = null;
        try {
            if (listaComodines.size() > 0) {
                nuevoComodin = (Comodin) Class.forName("domain.comodines." + listaComodines.get(random))
                        .getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        this.comodin = nuevoComodin;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void removeFicha() {
        this.ficha = null;
    }

}
