package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import domain.*;

/**
 * Clase que crea la ventana del juego
 * 
 * @Version 0.1
 */
public class RulesWindow {
	private CardLayout card;
	private daPOOsGUI gui;
	private JPanel principal;
	private JLabel fondoRW, reglas;
	private Paint impresora;

	/**
	 * Constructor
	 * 
	 * @param gui Ventana principal
	 */
	public RulesWindow(daPOOsGUI gui) {
		this.gui = gui;
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
	 * Prepara los elementos de la ventana
	 */
	private void prepareElements() {
		fondoRW = new JLabel();
		reglas = new JLabel();
		reglas.setFont(new Font("arial", 3, 20));
		reglas.setForeground(Color.WHITE);
		fondoRW.setVisible(true);
	}

	/**
	 * Modifica los tamannos de las etiquetas
	 */
	private void prepareSizes() {
		fondoRW.setBounds(0, 0, 1080, 730);
		reglas.setBounds(50, 0, 980, 620);
	}

	/**
	 * Agrega el texto a la etiqueta
	 */
	private void prapareImages() {
		impresora.pintarImagen(fondoRW, "./img/fondos/fondo.png", 1080, 730);
		reglas.setText(
				"<html><GANAR EL JUEGO: Gana el jugador que deje sin fichas al adversario.<p>"
						+ "<p><H1>PEON</H1><p>"
						+ "<p><H2>MOVIMIENTO PEON:Solo puede avanzar una casilla en alguna de sus diagonales.</H2><p>"
						+ "<p><H1>REINA</H1><p>"
						+ "<p><H2>MOVIMIENTO REINA: Puede mover a lo largo de sus diagonales.</H2><p>"
						+ "<p><H1>COMER FICHA<H1><p>"
						+ "<p><H1>CASILLA TELEPORT</H1><p>"
						+ "<p>Al caer en la casilla la ficha del jugador se mueve a una casilla vacia aleatoriamente<p>"
						+ "<p><H1>CASILLA MINE</H1><p>"
						+ "<p>Al caer en la casilla la ficha del jugador activa una bomba que explota en un radio de 3x3<p>");
	}

	/**
	 * Agrega los componentes a la ventada
	 */
	private void addComponents() {
		fondoRW.add(reglas);
		principal.add(fondoRW, "FondoRW");
	}

	private void prepareActions() {
		// TODO Auto-generated method stub
	}
}
