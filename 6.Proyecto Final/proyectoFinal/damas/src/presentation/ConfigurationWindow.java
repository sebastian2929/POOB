package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.*;

/**
 * Clase que crea la ventana de configuracion
 *
 * @version 0.1
 */
public class ConfigurationWindow {

	private CardLayout card;
	private daPOOsGUI gui;
	private daPOOs juego;
	private GameWindow GW;
	private JPanel principal;
	private JTextField nombre;
	private JTextField porcentaje;
	private Integer porcentajeCasillas;
	private JLabel fondoCW, tituloC, jugador, lnombre,
			lcolor, numero, lcasillas, lporcentaje;
	private JButton iniciar, agregar;
	private Paint impresora;
	private Color color;
	private int noJugadores, actual;
	private static List<String> jugadoresNombre = new ArrayList<>();
	private static List<String> jugadoresColor = new ArrayList<>();
	private static List<String> casillas = new ArrayList<>();
	private List<JButton> coloresLista = new ArrayList<>();
	private List<JCheckBox> casillasLista = new ArrayList<>();
	private List<JButton> colorPressed = new ArrayList<>();

	/**
	 * Constructor de la ventana
	 * 
	 * @param gui Vista de la venta principal.
	 */
	public ConfigurationWindow(daPOOsGUI gui) {
		this.gui = gui;
		this.actual = 1;
		card = gui.getCard();
		principal = gui.getPrincipal();
		impresora = Paint.getPrinter();
		prepareElements();
		prepareWildCardBox();
		prepareColoredButtons();
		prepareSizes();
		prepareInvisibilitys();
		prapareImages();
		addComponents();
		prepareActions();
		actionPrepare();
		noJugadores = 2;
	}

	/**
	 * Crea los elementos visuales de la ventana.
	 */
	private void prepareElements() {
		fondoCW = new JLabel();
		tituloC = new JLabel();
		lcasillas = new JLabel();
		jugador = new JLabel();
		numero = new JLabel();
		lnombre = new JLabel();
		lcolor = new JLabel();
		nombre = new JTextField();
		porcentaje = new JTextField();
		iniciar = new JButton();
		agregar = new JButton();
		lporcentaje = new JLabel();
	}

	/**
	 * Crea las opciones de casillas a agregar al juego
	 */
	private void prepareWildCardBox() {
		String[] casillasNombre = { "Teleport", "Mine" };
		String[] tipsCasillas = { "Mueve una ficha a una posición aleatoria del tablero",
				"Al caer una ficha en esta se activa una bomba de radio 3x3" };
		for (int i = 0; i < casillasNombre.length; i++) {
			JCheckBox casilla = new JCheckBox(casillasNombre[i]);
			casilla.setToolTipText(tipsCasillas[i]);
			casilla.setContentAreaFilled(false);
			casilla.setFont(new Font("arial", 2, 20));
			casilla.setForeground(Color.WHITE);
			casilla.setBounds(760, 350 + i * 30, 200, 25);
			casilla.setVisible(false);
			fondoCW.add(casilla);
			casillasLista.add(casilla);
		}

	}

	/**
	 * Preapara los botones para que el jugador seleccione el color.
	 */
	private void prepareColoredButtons() {
		String[] tipsColores = { "Negro", "Blanco" };
		for (int i = 0; i < 2; i++) {
			JButton color = new JButton();
			color.setBounds(210 + i * 80, 355, 50, 50);
			color.setToolTipText(tipsColores[i]);
			if (i == 0) {
				color.setBackground(Color.BLACK);
			} else if (i == 1) {
				color.setBackground(Color.WHITE);
			}
			color.setVisible(false);
			color.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					actionColor(e);
				}
			});
			fondoCW.add(color);
			coloresLista.add(color);
		}
	}

	/**
	 * Modifica los tamannos de los componentes visuales de la ventana.
	 */
	private void prepareSizes() {
		tituloC.setBounds(330, 60, 420, 55);
		jugador.setBounds(150, 200, 255, 45);
		numero.setBounds(350, 190, 45, 45);
		lnombre.setBounds(50, 290, 253, 45);
		lcolor.setBounds(50, 360, 253, 45);
		lporcentaje.setBounds(550, 285, 255, 45);
		nombre.setBounds(240, 285, 253, 45);
		porcentaje.setBounds(810, 285, 60, 45);
		iniciar.setBounds(450, 500, 145, 45);
		agregar.setBounds(150, 500, 180, 45);
		lcasillas.setBounds(550, 360, 255, 45);
		agregar.setToolTipText("Agregar Jugador");
		nombre.setToolTipText("Ingresa Un Nombre");
	}

	/**
	 * Se modifican las caracteristicas de los componentes de la ventana.
	 */
	private void prepareInvisibilitys() {

		iniciar.setVisible(false);
		iniciar.setBorder(null);
		iniciar.setContentAreaFilled(false);
		agregar.setVisible(false);
		agregar.setBorder(null);
		agregar.setContentAreaFilled(false);
		tituloC.setVisible(false);
		jugador.setVisible(false);
		numero.setVisible(false);
		nombre.setVisible(false);
		porcentaje.setVisible(false);
		lnombre.setVisible(false);
		lcolor.setVisible(false);
		lcasillas.setVisible(false);
		lporcentaje.setVisible(false);
	}

	/**
	 * Agregan imagenes a las componentes de la ventana.
	 */
	private void prapareImages() {
		impresora.pintarImagen(fondoCW, "./img/fondos/fondo.png", 1080, 730);
		impresora.pintarImagen(tituloC, "./img/imagenes/Configuracion.png", 420, 55);
		impresora.pintarImagen(jugador, "./img/imagenes/Jugador.png", 253, 45);
		impresora.pintarImagen(lnombre, "./img/imagenes/Nombre.png", 253, 45);
		impresora.pintarImagen(lcolor, "./img/imagenes/Color.png", 253, 45);
		impresora.pintarImagen(iniciar, "./img/imagenes/Iniciar.png", 145, 45);
		impresora.pintarImagen(agregar, "./img/imagenes/Agregar.png", 180, 45);
		impresora.pintarImagen(numero, "./img/imagenes/1.png", 45, 45);
		impresora.pintarImagen(lcasillas, "./img/imagenes/Casillas.png", 180, 45);
		impresora.pintarImagen(lporcentaje, "./img/imagenes/porcentaje.png", 255, 45);

	}

	/**
	 * Agregar componentes a la etiqueta principal y al panel.
	 */
	private void addComponents() {
		fondoCW.add(tituloC);
		fondoCW.add(jugador);
		fondoCW.add(lcasillas);
		fondoCW.add(numero);
		fondoCW.add(nombre);
		fondoCW.add(porcentaje);
		fondoCW.add(lnombre);
		fondoCW.add(lcolor);
		fondoCW.add(lporcentaje);
		fondoCW.add(iniciar);
		fondoCW.add(agregar);
		principal.add(fondoCW, "FondoCW");
	}

	/**
	 * Pepara las acciones de cada boton.
	 */
	private void prepareActions() {
		iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				actionIniciar();
			}
		});
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				actionAgregar();
			}
		});
	}

	/**
	 * Prepara la ventana de configuracion.
	 */
	protected void actionPrepare() {
		tituloC.setVisible(true);
		jugador.setVisible(true);
		numero.setVisible(true);
		nombre.setVisible(true);
		porcentaje.setVisible(true);
		lnombre.setVisible(true);
		lcolor.setVisible(true);
		lporcentaje.setVisible(true);
		lcasillas.setVisible(true);
		agregar.setVisible(true);
		agregar.setBorder(null);
		agregar.setContentAreaFilled(false);
		for (JCheckBox casilla : casillasLista) {
			casilla.setVisible(true);
		}
		for (JButton color : coloresLista) {
			color.setVisible(true);
		}
	}

	/**
	 * Determina la accicion al presionar el boton agregar.
	 * Ente annade un jugador con su respectivo color.
	 */
	protected void actionAgregar() {
		if (this.color == null) {
			JOptionPane.showMessageDialog(null, "Falta Escojer El Color Del Jugador", "Alerta!", 1);
		} else if (nombre.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Falta Nombre Del Jugador", "Alerta!", 1);
		} else if (porcentaje.getText().equals("") && this.actual == 2) {
			JOptionPane.showMessageDialog(null, "Falta poner el porcentaje de casillas especiales", "Alerta!", 1);
		} else {
			jugadoresNombre.add(nombre.getText());
			jugadoresColor.add(this.color.toString());
			if (this.actual == 2) {
				porcentajeCasillas = Integer.parseInt(porcentaje.getText());
				if (porcentajeCasillas != null && porcentajeCasillas < 0 && porcentajeCasillas > 100) {
					porcentajeCasillas = 30;
				}
			}
			for (JButton color : coloresLista) {
				if (color.getBackground().equals(this.color)) {
					colorPressed.add(color);
				}
			}
			this.color = null;
			if (this.actual != this.noJugadores) {
				this.actual += 1;
				nombre.setText("");
				impresora.pintarImagen(numero, "./img/imagenes/" + this.actual + ".png", 45, 45);
			} else {
				agregar.setVisible(false);
				iniciar.setBorder(null);
				iniciar.setContentAreaFilled(false);
				iniciar.setVisible(true);

			}
		}
	}

	/**
	 * Inicia la partida con las configuraciones seleccionadas.
	 */
	public void actionIniciar() {
		escogerCasillas();
		juego = new daPOOs(jugadoresNombre, jugadoresColor, casillas, porcentajeCasillas);
		GW = new GameWindow(gui, juego);
		card.show(principal, "FondoGW");
	}

	/**
	 * Se escoge los comodines seleccionados.
	 */
	private void escogerCasillas() {
		for (JCheckBox casilla : casillasLista) {
			if (casilla.isSelected()) {
				casillas.add(casilla.getText());
			}
		}
	}

	/**
	 * Realiza la accion cuando se elije un color
	 * 
	 * @param e Evento generado por el mouse.
	 */
	public void actionColor(MouseEvent e) {
		JButton pressed = (JButton) e.getSource();
		pressed.setEnabled(false);
		for (JButton color : coloresLista) {
			if (color != pressed) {
				color.setEnabled(true);
				for (JButton used : colorPressed) {
					used.setEnabled(false);
				}
			}
		}
		if (pressed.getBackground() == Color.BLACK) {
			this.color = Color.BLACK;
		} else if (pressed.getBackground() == Color.WHITE) {
			this.color = Color.WHITE;
		}
	}

	/**
	 * Obtiene el juego creado
	 * 
	 * @return Juego actual
	 */
	public daPOOs getJuego() {
		return this.juego;
	}

	/**
	 * Obtiene la ventana del juego
	 * 
	 * @return Estado del juego
	 */
	public GameWindow getGameW() {
		return GW;
	}

}
