package presentation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import domain.*;

public class TableroGUI extends JFrame {
    private Paint Impresora;
    private JPanel fondo;
    private JPanel tablero;
    private JLabel[][] mapa;
    private JButton[][] regilla;
    private daPOOs juego;
    private ArrayList<JButton> fichas = new ArrayList<JButton>();
    private List<String> jugadores = new ArrayList<>();
    private List<String> colores = new ArrayList<>();
    private List<String> comodines = new ArrayList<>();

    public TableroGUI() {
        jugadores.add("Andres");
        jugadores.add("Sebastian");
        colores.add("Black");
        colores.add("White");
        comodines.add("Mine");
        comodines.add("Teleport");
        juego = new daPOOs(jugadores, colores, comodines,20);
        Impresora = Paint.getPrinter();
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setBounds(0, 0, 700, 730);
        fondo = new JPanel();
        tablero = new JPanel();
        tablero.setLayout(new GridLayout(10, 10, 0, 0));
        mapa = new JLabel[10][10];
        regilla = new JButton[10][10];
        Tablero tableroJuego = juego.getTableroJuego();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel casillon = new JLabel();
                JButton ficha = new JButton();
                Casilla casilla = tableroJuego.getCasilla(i, j);
                int posX = i;
                int posY = j;
                ficha.addActionListener(e -> actionPlay(posX, posY));
                if ((i + j) % 2 == 1) {
                    if (casilla != null && !casilla.estaVacia()) {
                        String color = casilla.getFicha().getColor();
                        Impresora.pintarImagen(ficha, "./img/jugador" + color + "/normal.png", 69, 69);
                    }
                    Impresora.pintarImagen(casillon, "./img/casillas/" + casilla.getTipo() + "/casillaNegra.png", 69,
                            69);
                    ficha.setBorder(null);
                    ficha.setContentAreaFilled(false);
                    ficha.setVisible(true);
                    ficha.setBounds(casillon.getWidth() / 2, casillon.getHeight() / 2, 69, 69);
                    casillon.add(ficha);
                    mapa[i][j] = casillon;
                    regilla[i][j] = ficha;
                    tablero.add(casillon);
                } else {
                    Impresora.pintarImagen(casillon, "./img/casillas/Basica/casillaBlanca.png", 69,
                            69);
                    mapa[i][j] = casillon;
                    tablero.add(casillon);
                    fichas.add(ficha);
                }

            }
        }
        fondo.add(tablero);
        add(fondo);

    }

    public void actionPlay(int posX, int posY) {
        this.juego.juego(posX, posY);
        refresh();
        repaint();
    }

    public void refresh() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton ficha = regilla[i][j];
                if (ficha != null) {
                    ficha.setIcon(null);
                    ficha.setBorder(null);
                    ficha.setContentAreaFilled(false);
                }
            }
        }
    }

    public void repaint() {
        Tablero tableroJuego = juego.getTableroJuego();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton ficha = regilla[i][j];
                JLabel casillaT = mapa[i][j];
                Casilla casilla = tableroJuego.getCasilla(i, j);
                if (casilla != null && !casilla.estaVacia()) {
                    String color = casilla.getFicha().getColor();
                    String tipo = casilla.getFicha().getTipo();
                    Impresora.pintarImagen(ficha, "./img/jugador" + color + "/" + tipo + ".png", 69, 69);
                    ficha.setBorder(null);
                    ficha.setContentAreaFilled(false);
                }
                if (casilla != null) {
                    Impresora.pintarImagen(casillaT, "./img/casillas/Basica/casillaNegra.png", 69,
                            69);
                }
            }
        }
    }

    /**
     * Main
     * 
     * @param args Los argumentos de la consola al ejecutar el programa.
     */
    public static void main(String[] args) {
        TableroGUI gui = new TableroGUI();
        gui.setVisible(true);
    }
}
