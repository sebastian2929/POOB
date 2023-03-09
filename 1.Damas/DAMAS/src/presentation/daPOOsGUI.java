package presentation;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.*;


/**
 * Presentacion del juego Poobchis
 * @author Angie Mojica - Daniel Santanilla
 * @version 0.4
 */
public class daPOOsGUI extends JFrame{
	
	private CardLayout card;
	private JMenuBar barraMenu;
	private JMenu opciones;
	private JPanel principal;
	private JMenuItem nuevo, abrir, salvar, salir, regresar;
	private JFileChooser selectorArchivos;
	private JButton jugar, reglas;
	private JLabel fondoPW, autores;
	private daPOOs juego;
	private Paint impresora;
	//private ConfigurationWindow CW;
	
	/**
	 * Constructor de la ventana principal.
	 */
	private daPOOsGUI() {
		impresora = Paint.getPrinter();
		prepareElements();
		prepareActions();
	}
	
	/** 
	 * Se prepara los elementos de la ventana.
	 */
	public void prepareElements() {
		card = new CardLayout();
		principal = new JPanel(card);
		setTitle("POOBCHIS");
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		add(principal);
		preparePrincipalWindow();
		
		prepareElementsMenu();
		card.show(principal, "FondoPW");
	    
	}
	
	/**
	 * Prepara los elementos visuales de la ventana principal
	 */
	public void preparePrincipalWindow() {
		fondoPW = new JLabel();
		JLabel titulo = new JLabel();
		jugar = new JButton("JUGAR");
	    reglas = new JButton("REGLAS");
	    autores = new JLabel("Creado por: Angie Natalia Mojica y Daniel Antonio Santanilla");
	    
		fondoPW.setBounds(0, 0, 1080, 720);
		jugar.setBounds(230, 350, 157, 81);
	    reglas.setBounds(600,350,173, 80);
	    autores.setBounds(0, 630, 600, 30);
	    autores.setFont(new Font("arial",1,20));
	    autores.setForeground(Color.WHITE);
	    titulo.setBounds(190, 60, 425, 171);
	    
	    jugar.setContentAreaFilled(false);

	    jugar.setBorder(null);
	    jugar.setToolTipText("Jugar");
	    reglas.setContentAreaFilled(false);
	    reglas.setBorder(null);
	    reglas.setToolTipText("Reglas");

	    
	    fondoPW.add(titulo);
	    fondoPW.add(jugar);
	    fondoPW.add(reglas);
	    fondoPW.add(autores);
	    principal.add(fondoPW, "FondoPW");
	}
	
	/**
	 * Prepara el menu de acciones.
	 */
	public void prepareElementsMenu() {
		barraMenu = new JMenuBar();
		opciones = new JMenu("Opciones");
		nuevo = new JMenuItem("Nuevo");
		abrir = new JMenuItem("Abrir");
		salvar = new JMenuItem("Guardar");
		salir = new JMenuItem("Salir");
		regresar = new JMenuItem("Regresar");
		opciones.add(nuevo);
		opciones.addSeparator();
		opciones.add(abrir);
		opciones.add(salvar);
		opciones.addSeparator();
		opciones.add(salir);
		barraMenu.add(opciones);
		barraMenu.add(regresar);
		selectorArchivos = new JFileChooser();
		setJMenuBar(barraMenu);
	}
	
	/**
	 * Prepara las acciones de la ventana.
	 */
	public void prepareActions() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	actionClose();
            }
        });
		//prepareActionsMenu();
		prepareActionsPrincipalWindow();
	}
	
	/**
	 * Prepara las acciones de los botones de juego.
	 */
	public void prepareActionsPrincipalWindow() {
		jugar.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event) {
				actionPlay();
				} 
		});
		reglas.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event) {
				actionRules();
				} 
		});
	}


    
    /**
     * Accion de cerrar la ventana.
     */
	public void actionClose(){
        int confirmation = JOptionPane.showConfirmDialog(null,"�Seguro que desea salir?","Cerrar",0);
        if(confirmation == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
	
	/**
	 * Accion del boton de jugar.
	 */
	public void actionPlay() {
		card.show(principal, "FondoCW");
	}
	
	/**
	 * Accion del boton de reglas.
	 */
	public void actionRules() {
		card.show(principal, "FondoRW");
	}
	
	/**
	 * Accion del boton reiniciar.
	 */
	public void actionReturn() {
		int confirmation = JOptionPane.showConfirmDialog(null,"�Seguro que desea regresar a la ventana principal?\n Perdera todo el progreso.","Regresar",0);
		if (confirmation == JOptionPane.YES_OPTION) {
			card.show(principal, "FondoPW");	 
		}	
	}
	
	/**
	 * Obtiene el panel principal
	 * @return JPanel del componente principal del gui.
	 */
	public JPanel getPrincipal() {
		return principal;
	}
	
	/**
	 * Obtiene el cardlayout de la ventana principal.
	 * @return CardLayoud con las vistas del gui.
	 */
	public CardLayout getCard() {
		return card;
	}
	
	/**
	 * Main
	 * @param args Los argumentos de la consola al ejecutar el programa.
	 */
	public static void main(String[] args) {
		daPOOsGUI gui = new daPOOsGUI();
		gui.setVisible(true);
	}
}
