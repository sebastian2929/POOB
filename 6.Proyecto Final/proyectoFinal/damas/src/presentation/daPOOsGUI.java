package presentation;

import java.awt.*;
import java.awt.event.*;
import java.io.File;


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.*;

/**
 * Presentacion del juego daPOOs
 * 
 * @version 0.1
 */
public class daPOOsGUI extends JFrame {

    private CardLayout card;
    private JMenuBar barraMenu;
    private JMenu opciones;
    private JPanel principal;
    private JMenuItem nuevo, abrir, salvar, salir, regresar;
    private JFileChooser selectorArchivos;
    private JButton jugar, reglas;
    private JLabel fondoPW, autores, titulo;
    private daPOOs juego;
    private Paint impresora;
    private ConfigurationWindow CW;
    private RulesWindow RW;

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
        setTitle("daPOOs");
        setSize(1080, 755);
        setLocationRelativeTo(null);
        setResizable(false);
        add(principal);
        preparePrincipalWindow();
        CW = new ConfigurationWindow(this);
        RW = new RulesWindow(this);
        prepareElementsMenu();
        card.show(principal, "FondoPW");
    }

    /**
     * Prepara los elementos visuales de la ventana principal
     */
    public void preparePrincipalWindow() {
        fondoPW = new JLabel();
        titulo = new JLabel();
        jugar = new JButton();
        reglas = new JButton();
        autores = new JLabel("Creado por: Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez");
        fondoPW.setBounds(0, 0, 1080, 755);
        jugar.setBounds(230, 410, 245, 60);
        reglas.setBounds(600, 410, 245, 60);
        autores.setBounds(160, 630, 900, 30);
        autores.setFont(new Font("Britannic", 3, 20));
        autores.setForeground(Color.WHITE);
        titulo.setBounds(327, 150, 426, 120);
        jugar.setToolTipText("Jugar");
        reglas.setToolTipText("Reglas");
        impresora.pintarImagen(fondoPW, "./img/fondos/fondo.png", 1080, 755);
        impresora.pintarImagen(titulo, "./img/texto/titulo.png", 426, 120);
        impresora.pintarImagen(jugar, "./img/texto/jugar.png", 245, 60);
        impresora.pintarImagen(reglas, "./img/texto/reglas.png", 245, 60);
        reglas.setContentAreaFilled(false);
        reglas.setBorder(null);
        jugar.setContentAreaFilled(false);
        jugar.setBorder(null);
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
        prepareActionsMenu();
        prepareActionsPrincipalWindow();
    }

    /**
     * Prepara las acciones de los botones de juego.
     */
    public void prepareActionsPrincipalWindow() {
        jugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPlay();
            }
        });
        reglas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionRules();
            }
        });
    }

    /**
     * Prepara las acciones del menu.
     */
    public void prepareActionsMenu() {
        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionOpen();
            }
        });
        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionSave();
            }
        });
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionClose();
            }
        });
        regresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionReturn();
            }
        });
    }

    /**
     * Accion de abrir un arhivo.
     */
    public void actionOpen() {
        File archivo = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo DAT (*.dat)", "dat");
        selectorArchivos.setFileFilter(filter);
        selectorArchivos.setVisible(true);
        int confirmation = selectorArchivos.showOpenDialog(abrir);
        if (confirmation == selectorArchivos.APPROVE_OPTION) {
            archivo = selectorArchivos.getSelectedFile();
        }
        selectorArchivos.setVisible(false);
        if (archivo == null) {
            JOptionPane.showMessageDialog(this, "Se cancelo la operacion de abrir.", "Informacion", 1);
        } else {
            try {
                juego = juego.abra(archivo);
                GameWindow GW = CW.getGameW();
                GW.actualizarJuego(juego);
                GW.refreshStats();
                TableroGUI tablero = GW.getTablero();
                // tablero.actualizarJuego(juego);
                tablero.refresh();
            } catch (daPOOsException pe) {
                JOptionPane.showMessageDialog(this, pe.getMessage(), "Informacion", 1);
            }
        }
    }

    /**
     * Accion de guardar un archivo.
     */
    public void actionSave() {
        juego = CW.getJuego();
        if (juego == null) {
            JOptionPane.showMessageDialog(this, "Se esta tratando de guardar una partida no iniciada.", "Informacion",
                    1);
        } else {
            File archivo = null;
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo DAT (*.dat)", "dat");
            selectorArchivos.setFileFilter(filter);
            selectorArchivos.setVisible(true);
            int confirmation = selectorArchivos.showSaveDialog(salvar);
            if (confirmation == selectorArchivos.APPROVE_OPTION) {
                archivo = new File(selectorArchivos.getSelectedFile() + ".dat");
            }
            selectorArchivos.setVisible(false);
            if (archivo == null) {
                JOptionPane.showMessageDialog(this, "Se cancelo la operacion de guardar.", "Informacion", 1);
            } else {
                try {
                    juego.guarde(archivo);
                } catch (daPOOsException pe) {
                    System.out.println(pe.getMessage());
                }
            }
        }
    }

    /**
     * Accion de cerrar la ventana.
     */
    public void actionClose() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Seguro que desea salir?", "Cerrar", 0);
        if (confirmation == JOptionPane.YES_OPTION) {
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
        int confirmation = JOptionPane.showConfirmDialog(null,
                "�Seguro que desea regresar a la ventana principal?\n Perdera todo el progreso.", "Regresar", 0);
        if (confirmation == JOptionPane.YES_OPTION) {
            card.show(principal, "FondoPW");
        }
    }

    /**
     * Obtiene el panel principal
     * 
     * @return JPanel del componente principal del gui.
     */
    public JPanel getPrincipal() {
        return principal;
    }

    /**
     * Obtiene el cardlayout de la ventana principal.
     * 
     * @return CardLayoud con las vistas del gui.
     */
    public CardLayout getCard() {
        return card;
    }

    /**
     * Main
     * 
     * @param args Los argumentos de la consola al ejecutar el programa.
     */
    public static void main(String[] args) {
        daPOOsGUI gui = new daPOOsGUI();
        gui.setVisible(true);
    }
}
