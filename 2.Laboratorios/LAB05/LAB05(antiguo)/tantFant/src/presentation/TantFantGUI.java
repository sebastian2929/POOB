package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.io.*;

public class TantFantGUI extends JFrame {
    private JMenuBar menu;
    private JMenu opciones;
    private JMenuItem nuevo, abrir, salvar, salir;
    private JFileChooser archivos;
    private JPanel tablero, informacion, seleccion, fondo, movimientos, turnoJugador;
    private JButton colores, reiniciar, terminar;
    private JSpinner cantidadMove;
    private JTextField turno;
    private JButton[] regilla;

    private TantFantGUI(String title) {
        super.setTitle(title);
        prepareElements();
        prepareActions();
    }

    public static void main(String[] arguments) {
        TantFantGUI gui = new TantFantGUI("Tant Fant");
        gui.setVisible(true);
    }

    public void prepareElements() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int alto = dimension.height;
        int ancho = dimension.width;
        setSize(ancho / 2, alto / 2);
        prepareElementsMenu();
        prepareElementsBoard();
    }

    public void prepareElementsMenu() {
        menu = new JMenuBar();
        opciones = new JMenu("Opciones");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        salir = new JMenuItem("Salir");
        opciones.add(nuevo);
        opciones.add(abrir);
        opciones.add(salvar);
        opciones.add(salir);
        menu.add(opciones);
        archivos = new JFileChooser();
        setJMenuBar(menu);
    }

    public void prepareElementsBoard() {
        tablero = new JPanel();
        informacion = new JPanel();
        seleccion = new JPanel();
        fondo = new JPanel();
        movimientos = new JPanel();
        turnoJugador = new JPanel();
        colores = new JButton("Colores");
        reiniciar = new JButton("Reiniciar");
        terminar = new JButton("Terminar");
        cantidadMove = new JSpinner();
        tablero.setLayout(new BorderLayout());
        informacion.setLayout(new BorderLayout());
        seleccion.setLayout(new BorderLayout());
        fondo.setLayout(new BorderLayout());
        tablero.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new TitledBorder("Tablero")));
        informacion.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new TitledBorder("Partida")));
        seleccion.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new TitledBorder("Opciones Juego")));
        tablero.setLayout(new GridLayout(3, 3));
        regilla = new JButton[3];
        for (int i = 0; i > 3; i++) {
            JButton ficha = new JButton();
            regilla[i] = ficha;
            tablero.add(ficha);
        }
        cantidadMove = new JSpinner();
        cantidadMove.setEnabled(false);
        movimientos.add(new JLabel("Movimientos: "));
        movimientos.add(cantidadMove);
        turno = new JTextField("1");
        turno.setEnabled(false);
        turnoJugador.add(new JLabel("Jugador en Turno: "));
        turnoJugador.add(turno);
        informacion.setLayout(new GridLayout(1, 2));
        informacion.add(movimientos);
        informacion.add(turnoJugador);
        seleccion.setLayout(new GridLayout(1, 3));
        seleccion.add(reiniciar);
        seleccion.add(colores);
        seleccion.add(terminar);
        fondo.add(tablero, BorderLayout.CENTER);
        fondo.add(informacion, BorderLayout.NORTH);
        fondo.add(seleccion, BorderLayout.SOUTH);
        add(fondo);
    }

    public void prepareActions() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                actionClose();
            }
        });
        prepareActionsMenu();
    }

    public void prepareActionsMenu() {
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionClose();
            }
        });
        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionSave();
            }
        });
        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionOpen();
            }
        });
    }

    public void actionClose() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Seguro que desea salir?", "Cerrar", 0);
        if (confirmation == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void actionOpen() {
        File archivo = null;
        archivos.setVisible(true);
        int confirmation = archivos.showOpenDialog(abrir);
        if (confirmation == JFileChooser.APPROVE_OPTION) {
            archivo = archivos.getSelectedFile();
        }
        JOptionPane.showMessageDialog(abrir,
                "La accion para abrir el archivo " + archivo.getName() + " esta en construccion.", "Informacion", 1);
        archivos.setVisible(false);
    }

    public void actionSave() {
        File archivo = null;
        archivos.setVisible(true);
        int confirmation = archivos.showSaveDialog(salvar);
        if (confirmation == JFileChooser.APPROVE_OPTION) {
            archivo = archivos.getSelectedFile();
        }
        JOptionPane.showMessageDialog(salvar,
                "La accion para guardar el archivo " + archivo.getName() + " esta en construccion.", "Informacion", 1);
        archivos.setVisible(false);
    }
}