package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.io.*;
import domain.*;

/**
 * Presentacion del juego
 * 
 * @Autor Andres Arias - Sebastian Blanco
 * @Version 13-11-22
 */

public class TantFantGUI extends JFrame {
    
    private String tipo;
    private JMenuBar menu;
    private JMenu opciones;
    private JMenuItem nuevo, abrir, salvar, salir;
    private JFileChooser archivos;
    private JPanel informacion, seleccion, fondo, movimientos, turnoJugador, tablero;
    private JButton colores, reiniciar, terminar;
    private JButton[][] regilla;
    private JSpinner cantidadMove;
    private JTextField turno;
    private TantFant tantfant;

    /**
     * Constructor de la clase
     * 
     * @param title titulo de la interfas(JFrame)
     */
    private TantFantGUI(String title) {
        super.setTitle(title);
        prepareElements();
        prepareActions();

    }

    /**
     * Prepara los elementos, la vista
     */
    public void prepareElements() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int alto = dimension.height;
        int ancho = dimension.width;
        setSize(ancho / 2, alto / 2);
        setLocationRelativeTo(null);
        prepareElementsMenu();
        prepareElementsBoard();
    }

    /**
     * Preparar elementos del menu
     */
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

    /**
     * Metodo que prepara el board
     */
    public void prepareElementsBoard() {
        tantfant = new TantFant();
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
        informacion.setLayout(new BorderLayout());
        seleccion.setLayout(new BorderLayout());
        fondo.setLayout(new BorderLayout());
        tablero.setLayout(new BorderLayout());
        tablero.setLayout(new GridLayout(3, 3));

        tablero.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new TitledBorder("Tablero")));
        informacion.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new TitledBorder("Partida")));
        seleccion.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new TitledBorder("Opciones Juego")));

        regilla = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton ficha = new JButton();
                regilla[i][j] = ficha;
                int x = i;
                int y = j;
                ficha.addActionListener(e -> actionPlay(tantfant.getJugadorEnTurno(), x, y));
                if (i == 0) {
                    ficha.setBackground(Color.black);
                    ficha.setOpaque(true);
                }
                if (i == 2) {
                    ficha.setBackground(Color.blue);
                    ficha.setOpaque(true);
                }

                tablero.add(ficha);
            }
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

    /**
     * Metodo para actualizar la vista del tablero en el GUI
     */
    public void refresh() {
        Tablero tablero = tantfant.getTablero();
        Ficha[][] fichas = tablero.getFichasTablero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton fichaColor = regilla[i][j];
                Ficha ficha = fichas[i][j];
                if (ficha != null) {
                    String color = ficha.getColor();
                    if (color.equals("blue")) {
                        fichaColor.setBackground(Color.blue);
                    } else if (color.equals("black")) {
                        fichaColor.setBackground(Color.black);
                    }
                } else {
                    fichaColor.setBackground(null);
                }

            }
        }
        turno.setText(String.valueOf(tantfant.getJugadorEnTurno().getIdJugador()));
    }

    /**
     * Metodo que prepara las acciones
     */
    public void prepareActions() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                actionClose();
            }
        });
        prepareActionsMenu();
        colores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Seleccione un Color", Color.black);
                tablero.setBackground(color);
            }
        });
    }

    /**
     * metodo que prepara el menu de las acciones
     */
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

        reiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionRestart();
            }
        });

        terminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionTerminar();
            }
        });

    }

    /**
     * Accion que que cierra el programa
     */
    public void actionClose() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Seguro que desea salir?", "Cerrar", 0);
        if (confirmation == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Accion que abre un archivo existente
     */
    public void actionOpen() {
        File archivo = null;
        archivos.setVisible(true);
        int confirmation = archivos.showOpenDialog(abrir);
        if (confirmation == archivos.APPROVE_OPTION) {
            archivo = archivos.getSelectedFile();
        }
        JOptionPane.showMessageDialog(abrir,
                "La accion para abrir el archivo " + archivo.getName() + " esta en construccion.", "Informacion", 1);
        archivos.setVisible(false);
    }

    /**
     * Accion que guarda la partida
     */
    public void actionSave() {
        File archivo = null;
        archivos.setVisible(true);
        int confirmation = archivos.showSaveDialog(salvar);
        if (confirmation == archivos.APPROVE_OPTION) {
            archivo = archivos.getSelectedFile();
        }
        JOptionPane.showMessageDialog(salvar,
                "La accion para guardar el archivo " + archivo.getName() + " esta en construccion.", "Informacion", 1);
        archivos.setVisible(false);
    }

    /**
     * Accion que reinicia el juego
     */
    public void actionRestart() {
        this.tantfant = new TantFant();
        this.cantidadMove = new JSpinner();
        refresh();
    }

    /**
     * Accion que termina la aplicacion
     */
    public void actionTerminar() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Seguro que desea salir?", "Cerrar", 0);
        if (confirmation == JOptionPane.YES_OPTION) {
            tipo = "mundo";
            System.out.println(tipo);
            System.exit(0);
        }
    }

    /**
     * Accion con la que se juega el juego
     * 
     * @param judador
     * @param x       posicion en X
     * @param y       posicion en Y
     */
    public void actionPlay(Jugador judador, int x, int y) {
        tantfant.juego(judador, x, y);
        cantidadMove.setValue(cantidadMove.getNextValue());
        refresh();
        if (tantfant.ganador()) {
            int confirmation = JOptionPane.showConfirmDialog(null,
                    "gano el jugador: " + String.valueOf(tantfant.getJugadorEnTurno().getIdJugador()), "ganador",
                    JOptionPane.DEFAULT_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    /**
     * Metodo principal
     * 
     * @param arguments
     */
    public static void main(String[] arguments) {
        TantFantGUI gui = new TantFantGUI("Tant Fant");
        gui.setVisible(true);
    }

}
