package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.*;

/**
 * Clase que crea la ventana del juego
 * 
 * @version 0.1
 */
public class GameWindow {
	private TableroGUI tablero;
	private CardLayout card;
	private daPOOs juego;
	private daPOOsGUI gui;
	private JPanel principal;
	private JLabel fondoGW, jugadorEnTurno, lblnombre, lblcolor, lblcoronadas,
			lblcapturadas, visual, coronadas, capturadas, nombre;
	private JButton color, abandonar;
	private Paint impresora;

	/**
	 * Constructor de la ventana de juego
	 * 
	 * @param gui  Ventana principal
	 * @param game Juego a mostrar
	 */
	public GameWindow(daPOOsGUI gui, daPOOs game) {
		this.gui = gui;
		juego = game;
		card = gui.getCard();
		principal = gui.getPrincipal();
		impresora = Paint.getPrinter();
		prepareElements();
		prepareSizes();
		prapareImages();
		addComponents();
		prepareActions();
	}

	/**
	 * Prepara los componentes a mostrar en la ventana
	 */
	public void prepareElements() {
		Jugador jugador = juego.getJugadorTurno();
		String nombreJ = jugador.getName();
		String colorJ = jugador.getColor();
		Color col = null;
		if (colorJ.equals("Black")) {
			col = Color.BLACK;
		} else {
			col = Color.WHITE;
		}
		tablero = new TableroGUI(juego, this);
		fondoGW = new JLabel();
		fondoGW.setVisible(true);
		abandonar = new JButton();
		jugadorEnTurno = new JLabel();
		lblnombre = new JLabel();
		lblcolor = new JLabel();
		lblcoronadas = new JLabel();
		lblcapturadas = new JLabel();
		visual = new JLabel();
		nombre = new JLabel(nombreJ);
		color = new JButton();
		color.setBackground(col);
		coronadas = new JLabel("0");
		capturadas = new JLabel("0");
		if (colorJ.equals("Black")) {
			color.setBackground(Color.BLACK);
		} else {
			color.setBackground(Color.WHITE);
		}
	}

	/**
	 * Modifica los tamannos de cada componente
	 */
	public void prepareSizes() {
		fondoGW.setBounds(0, 0, 1080, 755);
		visual.setBounds(0, 0, 660, 660);
		jugadorEnTurno.setBounds(760, 30, 255, 85);
		lblnombre.setBounds(700, 160, 255, 45);
		lblcolor.setBounds(700, 260, 255, 45);
		lblcoronadas.setBounds(700, 360, 255, 45);
		lblcapturadas.setBounds(700, 460, 255, 45);
		nombre.setBounds(890, 160, 255, 45);
		color.setBounds(850, 260, 50, 50);
		coronadas.setBounds(950, 360, 255, 45);
		capturadas.setBounds(970, 460, 255, 45);
		abandonar.setContentAreaFilled(false);
		abandonar.setBorder(null);
		abandonar.setBounds(830, 620, 245, 40);
		nombre.setFont(new Font("arial", 3, 25));
		coronadas.setFont(new Font("arial", 3, 50));
		capturadas.setFont(new Font("arial", 3, 50));
		coronadas.setForeground(Color.RED);
		capturadas.setForeground(Color.RED);
		nombre.setForeground(Color.RED);
		color.setEnabled(false);
	}

	/**
	 * Prepara las imagenes para los componentes de la ventana
	 */
	public void prapareImages() {
		impresora.pintarImagen(fondoGW, "./img/fondos/fondo.png", 1080, 755);
		impresora.pintarImagen(jugadorEnTurno, "./img/imagenes/JugadorEnTurno.png", 255, 85);
		impresora.pintarImagen(lblnombre, "./img/imagenes/Nombre.png", 255, 45);
		impresora.pintarImagen(lblcolor, "./img/imagenes/Color.png", 255, 45);
		impresora.pintarImagen(lblcoronadas, "./img/imagenes/Coronadas.png", 255, 45);
		impresora.pintarImagen(lblcapturadas, "./img/imagenes/Capturadas.png", 255, 45);
		impresora.pintarImagen(abandonar, "./img/imagenes/Abandonar.png", 255, 45);
		abandonar.setContentAreaFilled(false);
		abandonar.setBorder(null);
	}

	/**
	 * Agrega cada componente a la etiqueta principal
	 */
	public void addComponents() {
		fondoGW.add(tablero);
		fondoGW.add(jugadorEnTurno);
		fondoGW.add(lblnombre);
		fondoGW.add(lblcolor);
		fondoGW.add(lblcoronadas);
		fondoGW.add(abandonar);
		fondoGW.add(lblcapturadas);
		fondoGW.add(nombre);
		fondoGW.add(color);
		fondoGW.add(coronadas);
		fondoGW.add(capturadas);
		fondoGW.add(tablero);
		principal.add(fondoGW, "FondoGW");
	}

	/**
	 * Prepara las acciones de cada boton.
	 */
	public void prepareActions() {
		abandonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				actionLeave();
			}
		});
	}

	/**
	 * Abandona la partida notificando al ganador
	 * esto se da con la persona que tiene la menor cantidad
	 * de casillas restantes.
	 */
	protected void actionLeave() {
		int resp = JOptionPane.showConfirmDialog(null, "�Desea abandonar la partida?", "Abandonar",
				JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION) {
			Jugador ganador = juego.getWinner();
			JOptionPane.showMessageDialog(null, "El ganador es: " + ganador.getName(), "Felicidades", 1);
			System.exit(0);
		}
	}

	/**
	 * Actualiza la informacion de cada jugador.
	 */
	public void refreshStats() {
		Jugador jugador = juego.getJugadorTurno();
		String nombreJ = jugador.getName();
		String colorJ = jugador.getColor();
		int fichaCapturada = jugador.getFichasCapturadas();
		int fichaCoronadas = jugador.getFichasCoronodas();
		nombre.setText(nombreJ);
		capturadas.setText(String.valueOf(fichaCapturada));
		coronadas.setText(String.valueOf(fichaCoronadas));
		if (colorJ.equals("Black")) {
			color.setBackground(Color.BLACK);
		} else {
			color.setBackground(Color.WHITE);
		}
	}

	/**
	 * Obtiene el tablero visual.
	 * 
	 * @return Interfaz del tablero.
	 */
	public TableroGUI getTablero() {
		return tablero;
	}

	/**
	 * Actualiza el juego.
	 * 
	 * @param juego Nuevo juego.
	 */
	public void actualizarJuego(daPOOs juego) {
		refreshStats();
		this.juego = juego;
	}

	/**
	 * Actualiza el tablero.
	 * 
	 * @param tablero Nuevo tablero visual.
	 */
	public void actualizarTablero(TableroGUI tablero) {
		refreshStats();
		this.tablero = tablero;
	}
}
