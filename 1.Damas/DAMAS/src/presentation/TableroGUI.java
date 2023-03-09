package presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import domain.*;

public class TableroGUI extends JPanel{
    private Paint Impresora;
    private JPanel fondo;
    private JLabel[][] mapa;
    private daPOOs juego;
    private VentanaPrincipal ventana;
    private JButton[][] casillas;
    private ArrayList<JButton> fichas = new ArrayList<JButton>();
    private List<String> jugadores = new ArrayList<>();
	private List<String> colores = new ArrayList<>();
    private List<String> comodines = new ArrayList<>();

    public TableroGUI(){
        jugadores.add("Andres");
		jugadores.add("Sebastian");
		colores.add("Black");
		colores.add("White");
        juego = new daPOOs(jugadores,colores,comodines);
        Impresora = Paint.getPrinter();
        iniciarComponentes();
		iniciarTablero();
    }

    public TableroGUI(daPOOs juego, VentanaPrincipal ventana){
        this.ventana = ventana;
        this.juego = juego;
        Impresora = Paint.getPrinter();
        iniciarComponentes();
		iniciarTablero();
    }

    private void iniciarComponentes(){
        mapa = new JLabel[10][10];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                JLabel casilla = new JLabel();
                JButton ficha = new JButton();
                if( i+j % 2 == 1){
                    Impresora.pintarImagen(casilla,"./img/casillas/casillaNegra.png" , 30, 30);
                    casilla.add(ficha);
                    mapa[i][j]= casilla;
                }
                else{
                    Impresora.pintarImagen(casilla,"./img/casillas/casillaBlanca.png" , 30, 30);
                    mapa[i][j]= casilla;
                }
            }
        }
    }

    private void iniciarTablero(){
        ArrayList<Jugador> jugadores = juego.getJugadores();
        for(Jugador j: jugadores){
            String color = j.getColor();
            for(Ficha ficha: j.getFichas()){
                int posX = ficha.getPosX();
                int posY = ficha.getPosY();
                JLabel casilla = mapa[posX][posY];
                JButton botonFicha = new JButton();
                botonFicha.setContentAreaFilled(false);
                botonFicha.setBorder(null);
                botonFicha.addActionListener(e->actionPlay(ficha));
                Impresora.pintarImagen(botonFicha,"./img/jugador"+ color +"/normal.png" , 25, 25);
                //menos uno en X y Y
                casilla.add(botonFicha);
                mapa[posX][posY] = casilla;
                fichas.add(botonFicha);
            }
        }
        for(int i=0; i < 10;i++){
            for(int j = 0; i < 10; j++){
                fondo.add(mapa[i][j]);
            }
        }
    }

    public void actionPlay(Ficha ficha){
        int posX = ficha.getPosX();
        int posY = ficha.getPosY();
        this.juego.juego(posX,posY);
        refresh();
    }

    //por si se queda algo bugueado lo refresca
    public void refresh(){
        
    }

    public void actualizarJuego(daPOOs juego){
        this.juego = juego;
        fichas = new ArrayList<JButton>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                JLabel casilla = mapa[i][j];
                casilla.removeAll();
            }
        }

        for(Ficha ficha: juego.getTableroJuego().getFichaTablero()){
            String color = ficha.getColor();
            JButton botonFicha = new JButton();
            int posX = ficha.getPosX();
            int posY = ficha.getPosY();
            botonFicha.setContentAreaFilled(false);
            botonFicha.setBorder(null);
            botonFicha.addActionListener(e->actionPlay(ficha));
            Impresora.pintarImagen(botonFicha,"./img/jugador"+ color +"/normal.png" , 25, 25);
            JLabel casilla = mapa[posX][posY];
            casilla.add(botonFicha);
            mapa[posX][posY] = casilla;
            fichas.add(botonFicha);
        }
    }

    /**
	 * Main
	 * @param args Los argumentos de la consola al ejecutar el programa.
	 */
	public static void main(String[] args) {
		TableroGUI gui = new TableroGUI();
		gui.setVisible(true);
	}
}

