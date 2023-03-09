package presentation;

import domain.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Presentacion de AManuFacturing
 * 
 * @author Andres Arias - Sebastian Blanco
 * @version 25-11-22
 */
public class AManufacturingGUI extends JFrame {
    public static final int SIDE = 11;

    public final int SIZE;
    private JMenuBar menu;
    private JButton ticTacButton;
    private JPanel controlPanel;
    private JFileChooser archivos;
    private PhotoAManufacturing photo;
    private JMenu archivo;
    private AManufacturing aManufacturing;
    private JMenuItem abrir, guardarComo, nuevo, importar, exportarComo, salir;

    /**
     * Contructor de la interfaz para AManuFacturing
     */
    private AManufacturingGUI() {
        aManufacturing = new AManufacturing();
        SIZE = aManufacturing.getSize();
        prepareElements();
        prepareActions();
    }

    /**
     * Perapara los elementos que tiene la ventana de presentacion
     */
    private void prepareElements() {
        setTitle("aManufacturing celular");
        photo = new PhotoAManufacturing(this);
        ticTacButton = new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo, BorderLayout.NORTH);
        add(ticTacButton, BorderLayout.SOUTH);
        setSize(new Dimension(SIDE * SIZE + 15, SIDE * SIZE + 72));
        setResizable(false);
        photo.repaint();
        prepareElementsMenu();
    }

    /**
     * Preparar elementos del menu
     */
    public void prepareElementsMenu() {
        menu = new JMenuBar();
        archivo = new JMenu("Archivo");
        nuevo = new JMenuItem("nuevo");
        abrir = new JMenuItem("Abrir");
        guardarComo = new JMenuItem("Guardar como");
        importar = new JMenuItem("Importar");
        exportarComo = new JMenuItem("Exportar como");
        salir = new JMenuItem("Salir");
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardarComo);
        archivo.add(importar);
        archivo.add(exportarComo);
        archivo.add(salir);
        archivos = new JFileChooser();
        menu.add(archivo);
        setJMenuBar(menu);
    }

    /**
     * Indica las acciones que debe cumplir cada boton en la ventana
     */
    private void prepareActions() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ticTacButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ticTacButtonAction();
                    }
                });
        prepareActionsMenu();
    }

    /**
     * Preparar las acciones que debe cumplir el menu
     */
    public void prepareActionsMenu() {
        nuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                opcionNuevo();
            }
        });
        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                opcionAbrir();
            }
        });

        guardarComo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                opcionGuardarComo();
            }
        });

        importar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                opcionImportar();
            }
        });

        exportarComo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                opcionExportarComo();
            }
        });

        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                opcionSalir();
            }
        });

    }

    /**
     * Metodo que crea un nuevo AManufacturing
     */
    public void opcionNuevo() {
        aManufacturing = new AManufacturing();
        photo.repaint();
    }

    /**
     * Motodo que abre un archivo de AManufacturing
     */
    public void opcionAbrir() {
        File archivo = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo DAT (*.dat)", "dat");
        archivos.setFileFilter(filter);
        archivos.setVisible(true);
        int confirmation = archivos.showOpenDialog(guardarComo);
        if (confirmation == archivos.APPROVE_OPTION) {
            archivo = archivos.getSelectedFile();
        }
        archivos.setVisible(false);
        if (archivo == null) {
            JOptionPane.showMessageDialog(this, "Se cancelo la operacion de abrir.", "Informacion", 1);
        } else {
            try {
                aManufacturing = AManufacturing.abra(archivo);
                photo.repaint();
            } catch (AManufacturingException ae) {
                JOptionPane.showMessageDialog(this, ae.getMessage(), "Informacion", 1);
            }
        }

    }

    /**
     * Metodo que gurada AManufacturing
     */
    public void opcionGuardarComo() {
        File archivo = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo DAT (*.dat)", "dat");
        archivos.setFileFilter(filter);
        archivos.setVisible(true);
        int confirmation = archivos.showSaveDialog(guardarComo);
        if (confirmation == archivos.APPROVE_OPTION) {
            archivo = new File(archivos.getSelectedFile() + ".dat");
        }
        archivos.setVisible(false);
        // En caso de que no se confirme un archivo
        if (archivo == null) {
            JOptionPane.showMessageDialog(this, "Se cancelo la operacion de guardar.", "Informacion", 1);
        } else {
            try {
                aManufacturing.guarde(archivo);
            } catch (AManufacturingException ae) {
                System.out.println(ae.getMessage());
            }
        }
    }

    /**
     * Metodo que importa un archivo para ejecutar un AManufacturing
     */
    public void opcionImportar() {
        File archivo = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Texto (*.txt)", "txt");
        archivos.setFileFilter(filter);
        archivos.setVisible(true);
        int confirmation = archivos.showOpenDialog(importar);
        if (confirmation == archivos.APPROVE_OPTION) {
            archivo = archivos.getSelectedFile();
        }
        archivos.setVisible(false);
        if (archivo == null) {
            JOptionPane.showMessageDialog(this, "Se cancelo la operacion de importar.", "Informacion", 1);
        } else {
            try {
                aManufacturing = aManufacturing.importe(archivo);
                photo.repaint();
            } catch (AManufacturingException ae) {
                JOptionPane.showMessageDialog(this, ae.getMessage(), "Informacion", 1);
            }
        }
    }

    /**
     * Metodo que exporta un AManufacturing
     */
    public void opcionExportarComo() {
        File archivo = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Texto (*.txt)", "txt");
        archivos.setFileFilter(filter);
        archivos.setVisible(true);
        int confirmation = archivos.showSaveDialog(exportarComo);
        if (confirmation == archivos.APPROVE_OPTION) {
            archivo = new File(archivos.getSelectedFile() + ".txt");
        }
        archivos.setVisible(false);
        if (archivo == null) {
            JOptionPane.showMessageDialog(this, "Se cancelo la operacion de exportar.", "Informacion", 1);
        } else {
            try {
                aManufacturing.exporte(archivo);
            } catch (AManufacturingException ae) {
                System.out.println(ae.getMessage());
            }
        }
    }

    /**
     * Metodo que sale de la aplicacion
     */
    public void opcionSalir() {
        System.exit(0);
    }

    private void ticTacButtonAction() {
        aManufacturing.ticTac();
        photo.repaint();
    }

    public AManufacturing getaManufacturing() {
        return aManufacturing;
    }

    public static void main(String[] args) {
        AManufacturingGUI ca = new AManufacturingGUI();
        ca.setVisible(true);
    }
}

class PhotoAManufacturing extends JPanel {
    private AManufacturingGUI gui;

    public PhotoAManufacturing(AManufacturingGUI gui) {
        this.gui = gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE * gui.SIZE + 10, gui.SIDE * gui.SIZE + 10));
    }

    public void paintComponent(Graphics g) {
        AManufacturing aManufacturing = gui.getaManufacturing();
        super.paintComponent(g);

        for (int c = 0; c <= aManufacturing.getSize(); c++) {
            g.drawLine(c * gui.SIDE, 0, c * gui.SIDE, aManufacturing.getSize() * gui.SIDE);
        }
        for (int f = 0; f <= aManufacturing.getSize(); f++) {
            g.drawLine(0, f * gui.SIDE, aManufacturing.getSize() * gui.SIDE, f * gui.SIDE);
        }
        for (int f = 0; f < aManufacturing.getSize(); f++) {
            for (int c = 0; c < aManufacturing.getSize(); c++) {
                if (aManufacturing.getThing(f, c) != null) {
                    g.setColor(aManufacturing.getThing(f, c).getColor());
                    if (aManufacturing.getThing(f, c).shape() == Thing.SQUARE) {
                        if (aManufacturing.getThing(f, c).isActive()) {
                            g.fillRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        } else {
                            g.drawRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        }
                    } else {
                        if (aManufacturing.getThing(f, c).isActive()) {
                            g.fillOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        } else {
                            g.drawOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        }
                    }
                }
            }
        }
    }
}