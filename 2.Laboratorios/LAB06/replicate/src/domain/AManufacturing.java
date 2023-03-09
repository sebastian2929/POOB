package domain;

import java.util.*;
import java.io.*;

/**
 * Red bidimensional de celdas que contienen elementos que pueden
 * estar en dos estados activos e inactivos.
 * 
 * @author Andres Arias - Sebastian Blanco
 * @version 25-11-22
 */
public class AManufacturing implements Serializable {
    static private int SIZE = 50;
    private Thing[][] lattice;

    /**
     * Constructor de la clase
     */
    public AManufacturing() {
        lattice = new Thing[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                lattice[r][c] = null;
            }
        }
        someThings();
    }

    /**
     * Metodo que retorna el tamaño
     * 
     * @return Size
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Metodo que obtiene un thing contenida en la matriz
     * 
     * @param r
     * @param c
     * @return Thing
     */

    public Thing getThing(int r, int c) {
        return lattice[r][c];
    }

    /**
     * Metodo que agrega un thing en la matiz
     * 
     * @param r
     * @param c
     * @param e
     */
    public void setThing(int r, int c, Thing e) {
        lattice[r][c] = e;
    }

    /**
     * Metodo que adiciona las celulas en la matriz
     */
    public void someThings() {
        Cell simba = new Cell(this, 1, 1, true);
        Cell dala = new Cell(this, 2, 2, true);
        Mimos mafasa = new Mimos(this, 1, 2, true);
        Mimos scar = new Mimos(this, 1, 3, true);
        Mimos rafiki = new Mimos(this, 1, 4, true);
        // Kriptonita uone = new Kriptonita(this, 49, 49, true);
        // Kriptonita two = new Kriptonita(this, 0, 0, true);
        // Kriptonita three = new Kriptonita(this, 49, 0, true);
        // Kriptonita four = new Kriptonita(this, 0, 49, true);
        Semaforo andres = new Semaforo(this, 3, 6, true);
        Semaforo sebastian = new Semaforo(this, 3, 8, true);
        Franja franja = new Franja(this, 25, 25, true);
    }

    /**
     * Metodo que verifica si la celula tiene un vecino activo
     * 
     * @param r
     * @param c
     * @return int
     */
    public int neighborsActive(int r, int c) {
        int num = 0;
        for (int dr = -1; dr < 2; dr++) {
            for (int dc = -1; dc < 2; dc++) {
                if ((dr != 0 || dc != 0) && inLatice(r + dr, c + dc) &&
                        (lattice[r + dr][c + dc] != null) && (lattice[r + dr][c + dc].isActive()))
                    num++;
            }
        }
        return (inLatice(r, c) ? num : 0);
    }

    /**
     * Metodo que verifica esta vacia
     * 
     * @param r
     * @param c
     * @return boolean
     */
    public boolean isEmpty(int r, int c) {
        return (inLatice(r, c) && lattice[r][c] == null);
    }

    /**
     * Metodo que verifica que se encuentre dentro de la matriz
     * 
     * @param r
     * @param c
     * @return boolean
     */
    private boolean inLatice(int r, int c) {
        return ((0 <= r) && (r < SIZE) && (0 <= c) && (c < SIZE));
    }

    /**
     * Metodo que marca el paso del tiempo para todas las celulas
     */
    public void ticTac() {
        for (int i = 0; i < lattice.length; i++) {
            for (int j = 0; j < lattice.length; j++) {
                if (lattice[i][j] != null) {
                    lattice[i][j].change();
                }
            }
        }
        for (int i = 0; i < lattice.length; i++) {
            for (int j = 0; j < lattice.length; j++) {
                if (lattice[i][j] != null) {
                    lattice[i][j].decide();
                }
            }
        }
    }

    /**
     * Metodo que abre un archivo
     * 
     * @param archivo
     * @return AManufacturing
     * @throws AManufacturingException
     */
    public static AManufacturing abra(File archivo) throws AManufacturingException {
        AManufacturing aManufacturing = null;
        try {
            if (!archivo.exists()) {
                throw new AManufacturingException(AManufacturingException.ARCHIVO_NO_ENCONTRADO);
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo));
            aManufacturing = (AManufacturing) in.readObject();
            in.close();
        } catch (Exception e) {
            throw new AManufacturingException(AManufacturingException.ARCHIVO_NO_ENCONTRADO);
        }
        return aManufacturing;
    }

    /**
     * Metodo que abre un archivo version
     * 
     * @param archivo
     * @return AManufacturing
     * @throws AManufacturingException
     */
    public static AManufacturing abra00(File archivo) throws AManufacturingException {
        throw new AManufacturingException(AManufacturingException.OPCION_CONSTRUCCION);
    }

    /**
     * Metodo que abre un archivo version 01
     * 
     * @param archivo
     * @return AManufacturing
     * @throws AManufacturingException
     */
    public static AManufacturing abra01(File archivo) throws AManufacturingException {
        AManufacturing aManufacturing = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo));
            aManufacturing = (AManufacturing) in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aManufacturing;
    }

    /**
     * Metodo que guarda un archivo
     * 
     * @param archivo
     * @throws AManufacturingException
     */
    public void guarde(File archivo) throws AManufacturingException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que guarda una archivo version inicial para el metodo guarde()
     * 
     * @param archivo
     * @throws AManufacturingException
     */
    public void guarde00(File archivo) throws AManufacturingException {
        throw new AManufacturingException(AManufacturingException.OPCION_CONSTRUCCION);
    }

    /**
     * Metodo que guarda un archivo version 01
     * 
     * @param archivo
     * @throws AManufacturingException
     */
    public void guarde01(File archivo) throws AManufacturingException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
            out.writeObject(lattice);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que importa un archivo
     * 
     * @param archivo
     * @return AManufacturing
     * @throws AManufacturingException
     */
    public static AManufacturing importe(File archivo) throws AManufacturingException {
        AManufacturing aManufacturing = new AManufacturing();
        try {
            if (!archivo.exists()) {
                throw new AManufacturingException(AManufacturingException.ARCHIVO_NO_ENCONTRADO);
            }
            BufferedReader bIn = new BufferedReader(new FileReader(archivo));
            String line = bIn.readLine();
            while (line != null) {
                line = line.trim();
                String[] data = line.split(" ");
                construccion(aManufacturing, data[0], data[1], data[2], data[3]);
                line = bIn.readLine();
            }
            bIn.close();
        } catch (Exception e) {
            throw new AManufacturingException(AManufacturingException.ARCHIVO_NO_ENCONTRADO);
        }
        return aManufacturing;
    }

    /**
     * Metodo que es la version inicial para el metodo importar
     * 
     * @param archivo
     * @return AManufacturing
     * @throws AManufacturingException
     */
    public static AManufacturing importe00(File archivo) throws AManufacturingException {
        throw new AManufacturingException(AManufacturingException.OPCION_CONSTRUCCION);
    }

    /**
     * Metodo que importa un archivo version 01
     * 
     * @param archivo
     * @return AManufacturing
     * @throws AManufacturingException
     */
    public static AManufacturing importe01(File archivo) throws AManufacturingException {
        AManufacturing aManufacturing = new AManufacturing();
        try {
            BufferedReader bIn = new BufferedReader(new FileReader(archivo));
            String line = bIn.readLine();
            while (line != null) {
                line = line.trim();
                String[] data = line.split(" ");
                construccion(aManufacturing, data[0], data[1], data[2], data[3]);
                line = bIn.readLine();
            }
            bIn.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return aManufacturing;
    }

    /**
     * Metodo que exporta un archivo
     * 
     * @param archivo
     * @throws AManufacturingException
     */
    public void exporte(File archivo) throws AManufacturingException {
        try {
            FileWriter out = new FileWriter(archivo);
            for (int i = 0; i < lattice.length; i++) {
                for (int j = 0; j < lattice.length; j++) {
                    if (getThing(i, j) != null) {
                        out.write(getThing(i, j).getClass().toString().replace("class", "").replace(" domain.", "") +
                                " " + String.valueOf(i) + " " + String.valueOf(j) + " "
                                + String.valueOf(getThing(i, j).isActive()) + "\n");
                    }
                }
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que es la version inicial para el metodo exportar()
     * 
     * @param archivo
     * @throws AManufacturingException
     */
    public void exporte00(File archivo) throws AManufacturingException {
        throw new AManufacturingException(AManufacturingException.OPCION_CONSTRUCCION);
    }

    /**
     * Metodo que crea un tipo de celula segun su entrada
     * 
     * @param ca
     * @param item
     * @param i
     * @param j
     * @param bandera
     */
    public static void construccion(AManufacturing ca, String item, String i, String j, String bandera) {
        boolean variable = true;
        if (bandera.equals("false")) {
            variable = false;
        }
        ;
        int row = Integer.parseInt(i);
        int column = Integer.parseInt(j);
        if (item.equals("Cell")) {
            new Cell(ca, row, column, variable);
        } else if (item.equals("Franja")) {
            new Franja(ca, row, column, variable);
        } else if (item.equals("Mimos")) {
            new Mimos(ca, row, column, variable);
        } else if (item.equals("Kriptonita")) {
            new Kriptonita(ca, row, column, variable);
        } else if (item.equals("Semaforo")) {
            new Semaforo(ca, row, column, variable);
        }
    }
}
