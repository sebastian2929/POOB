package presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.*;



public class VentanaPrincipal{
    private TableroGUI tablero;
    private daPOOs juego;
    private daPOOsGUI gui;
    private JPanel principal;
    private JLabel  fondo, jugadorUno, jugadorDos, nombreJ1, nombreJ2, colorJ1, colorJ2, capturadasJ1, capturadasJ2, cambiadasJ1, cambiadasJ2, comodinAJ1, comodinAJ2,
    txtnombreJ1, txtnombreJ2, txtcapturadasJ1, txtcapturadasJ2, txtcambiadasJ1, txtcambiadasJ2, txtcomodinAJ1, txtcomodinAJ2;
    private JButton rendirseJ1, rendirseJ2, txtcolorJ1, txtcolorJ2;
    private Paint Impresora;

    public VentanaPrincipal(daPOOsGUI gui, daPOOs juego){
        this.gui = gui;
        this.juego = juego;
        principal = gui.getPrincipal();
        Impresora = Paint.getPrinter();
        prepareElements();
		prepareSizes();
		prapareImages();
		addComponents();
		prepareActions();
    }

    public void prepareElements(){

        tablero = new TableroGUI(juego, this);
        fondo = new JLabel();
        fondo.setVisible(true);

        //Jugador 1
        Jugador jugador1 = juego.getJugadorTurno();
        String J1nombre = jugador1.getName();
        String J1color = jugador1.getColor();
        //String J1capturadas = jugador1.getCapturadas();
        //String J1Cambiadas = jugador1.getCambiadas();
        //String J1ComodinesAct = jugador1.getComodinesAct();

        rendirseJ1 = new JButton();
        txtcolorJ1 = new JButton();
        txtcolorJ1.setBackground(Color.getColor(J1color));
        jugadorUno = new JLabel();
        nombreJ1 = new JLabel();
        colorJ1 = new JLabel();
        capturadasJ1 = new JLabel();
        cambiadasJ1 = new JLabel();
        comodinAJ1 = new JLabel();
        txtnombreJ1 = new JLabel(J1nombre);
        //txtcapturadasJ1 = new JLabel(J1capturadas);
        //txtcambiadasJ1 = new JLabel(J1Cambiadas);
        //txtcomodinAJ1 = new JLabel(J1ComodinesAct);

        //Jugador 2
        Jugador jugador2 = juego.getEnemigo();
        String J2nombre = jugador2.getName();
        String J2color = jugador2.getColor();
        //String J2capturadas = jugador2.getCapturadas();
        //String J2Cambiadas = jugador2.getCambiadas();
        //String J2ComodinesAct = jugador2.getComodinesAct();

        rendirseJ2 = new JButton();
        txtcolorJ2 = new JButton();
        txtcolorJ2.setBackground(Color.getColor(J2color));
        jugadorDos = new JLabel();
        nombreJ2 = new JLabel();
        colorJ2 = new JLabel();
        capturadasJ2 = new JLabel();
        cambiadasJ2 = new JLabel();
        comodinAJ2 = new JLabel();
        txtnombreJ2 = new JLabel(J2nombre);
        //txtcapturadasJ2 = new JLabel(J2capturadas);
        //txtcambiadasJ2 = new JLabel(J2Cambiadas);
        //txtcomodinAJ2 = new JLabel(J2ComodinesAct);
    }

    /**
	 * Modifica los tamannos de cada componente
	 */
	public void prepareSizes() {
		fondo.setBounds(0, 0, 1080, 720);
		jugadorUno.setBounds(760, 0, 251, 151);
		nombreJ1.setBounds(690, 135, 146, 62);
		colorJ1.setBounds(690, 185, 121, 59);
		capturadasJ1.setBounds(690, 235, 196, 66);
		cambiadasJ1.setBounds(690, 285, 144, 67);
		comodinAJ1.setBounds(690, 338, 192, 103);
		txtnombreJ1.setBounds(830, 150, 130, 30);
		txtcolorJ1.setBounds(810, 200, 30, 30);
		txtcapturadasJ1.setBounds(880, 250, 130, 30);
		txtcambiadasJ1.setBounds(830, 305, 130, 30);
		txtcomodinAJ1.setBounds(875, 390, 130, 30);
		rendirseJ1.setBounds(930, 620, 136, 43);
		rendirseJ1.setContentAreaFilled(false);
		rendirseJ1.setBorder(null);
		txtnombreJ1.setFont(new Font("arial",1,20));
		txtcapturadasJ1.setFont(new Font("arial",1,20));
		txtcambiadasJ1.setFont(new Font("arial",1,20));
		txtcomodinAJ1.setFont(new Font("arial",1,20));
		txtcapturadasJ1.setForeground(Color.WHITE);
		txtcambiadasJ1.setForeground(Color.WHITE);
		txtcomodinAJ1.setForeground(Color.WHITE);
		txtnombreJ1.setForeground(Color.WHITE);
		colorJ1.setEnabled(false);
	}

	/**
	 * Prepara las imagenes para los componentes de la ventana
	 */
	public void prapareImages() {
		Impresora.pintarImagen(fondo, "./img/fondos/fondo.jpg", 1080, 720);
    }

    /**
	 * Agrega cada componente a la etiqueta principal 
	 */
	public void addComponents() {
		fondo.add(tablero);
		fondo.add(jugadorUno);
		fondo.add(nombreJ1);
		fondo.add(colorJ1);
		fondo.add(capturadasJ1);
		fondo.add(cambiadasJ1);
		fondo.add(comodinAJ1);
		fondo.add(txtnombreJ1);
		fondo.add(txtcolorJ1);
		fondo.add(txtcapturadasJ1);
		fondo.add(txtcambiadasJ1);
		fondo.add(txtcomodinAJ1);
        fondo.add(rendirseJ1);
		fondo.add(tablero);
		principal.add(fondo, "fondo");
	}

    /**
	 * Prepara las acciones de cada boton.
	 */
	public void prepareActions() {
		rendirseJ1.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event) {
				actionLeave();
				} 
		});
	}

    /**
	 * Abandona la partida notificando al ganador
	 */
	protected void actionLeave() {
		int resp = JOptionPane.showConfirmDialog(null, "�Desea abandonar la partida?", "Abandonar", JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION) {
			//Jugador ganador = juego.ganadorPartidaAbandonada();
			//JOptionPane.showMessageDialog(null, "El ganador es: "+ganador.getName(),"Felicidades",1);
			System.exit(0);
		}
	}
}
