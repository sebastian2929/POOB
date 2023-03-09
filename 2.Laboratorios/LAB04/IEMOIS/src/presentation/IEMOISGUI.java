package presentation; 

import domain.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * @version ECI 2020
 */
public class IEMOISGUI extends JFrame{

    private static final int PREFERRED_WIDTH = 700;
    private static final int PREFERRED_HIGH= 700;
    private static final Dimension PREFERRED_DIMENSION =
        new Dimension(PREFERRED_WIDTH,PREFERRED_HIGH);

    private IEMOIS programs;

    /*List*/
    private JButton buttonList;
    private JButton buttonRestartList;
    private JTextArea textDetails;

    /*Add*/
    private JTextField name;   
    private JTextField discount;
    private JTextArea  courses;
    private JButton buttonAdd;
    private JButton buttonRestartAdd;

    /*Search*/
    private JTextField textSearch;
    private JTextArea textResults;

    private IEMOISGUI(){
        programs=new IEMOIS();
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        setTitle("IEMOIS. Especializaciones y Cursos.");
        name = new JTextField(50);
        discount = new JTextField(50);
        courses = new JTextArea(10, 50);
        courses.setLineWrap(true);
        courses.setWrapStyleWord(true);

        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar",   prepareAreaList());
        etiquetas.add("Adicionar",  prepareAreaAdd());
        etiquetas.add("Buscar", prepareSearchArea());
        getContentPane().add(etiquetas);
        setSize(PREFERRED_DIMENSION);

    }

    /**
     * Prepare the area list
     * @return 
     */
    private JPanel prepareAreaList(){

        textDetails = new JTextArea(10, 50);
        textDetails.setEditable(false);
        textDetails.setLineWrap(true);
        textDetails.setWrapStyleWord(true);
        JScrollPane scrollArea =
            new JScrollPane(textDetails,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel  botones = new JPanel();
        buttonList = new JButton("Listar");
        buttonRestartList = new JButton("Limpiar");
        botones.add(buttonList);
        botones.add(buttonRestartList);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Prepara el area de adición
     * @return El area de adición
     */
    private JPanel prepareAreaAdd(){

        Box nameH = Box.createHorizontalBox();
        nameH.add(new JLabel("nombre", JLabel.LEFT));
        nameH.add(Box.createGlue());
        Box nameV = Box.createVerticalBox();
        nameV.add(nameH);
        nameV.add(name);

        Box discountH = Box.createHorizontalBox();
        discountH.add(new JLabel("precio o descuento", JLabel.LEFT));
        discountH.add(Box.createGlue());
        Box discountV = Box.createVerticalBox();
        discountV.add(discountH);
        discountV.add(discount);

        Box coursesH = Box.createHorizontalBox();
        coursesH.add(new JLabel("cursos", JLabel.LEFT));
        coursesH.add(Box.createGlue());
        Box coursesV = Box.createVerticalBox();
        coursesV.add(coursesH);
        coursesV.add(courses);

        Box singleLineFields = Box.createVerticalBox();
        singleLineFields.add(nameV);
        singleLineFields.add(discountV);       

        JPanel textDetailsPanel = new JPanel();
        textDetailsPanel.setLayout(new BorderLayout());
        textDetailsPanel.add(singleLineFields, BorderLayout.NORTH);
        textDetailsPanel.add(coursesV, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        buttonAdd = new JButton("Adicionar");
        buttonRestartAdd = new JButton("Limpiar");

        botones.add(buttonAdd);
        botones.add(buttonRestartAdd);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textDetailsPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * 
     * @return 
     */
    private JPanel prepareSearchArea(){

        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        textSearch = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(textSearch);

        textResults = new JTextArea(10,50);
        textResults.setEditable(false);
        textResults.setLineWrap(true);
        textResults.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(textResults,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel buttonListea = new JPanel();
        buttonListea.setLayout(new BorderLayout());
        buttonListea.add(busquedaArea, BorderLayout.NORTH);
        buttonListea.add(scrollArea, BorderLayout.CENTER);

        return buttonListea;
    }

    private void prepareActions(){
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent ev){
                    setVisible(false);
                    System.exit(0);
                }
            });

        /*List*/
        buttonList.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    actionList();
                }
            });

        buttonRestartList.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    textDetails.setText("");
                }
            });

        /*Add*/
        buttonAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev){
                    actionAdd();                    
                }
            });

        buttonRestartAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev){
                    name.setText("");
                    discount.setText("");
                    courses.setText("");
                }
            });

        /*Search*/
        textSearch.getDocument().addDocumentListener(new DocumentListener(){
                public void changedUpdate(DocumentEvent ev){
                    actionSearch();
                }

                public void insertUpdate(DocumentEvent ev){
                    actionSearch();
                }

                public void removeUpdate(DocumentEvent ev){
                    actionSearch();
                }

            });
    }    

    private void actionList(){
        textDetails.setText(programs.toString());
    }

    private void  actionAdd(){
        if (courses.getText().trim().equals("")){
            programs.addCourse(name.getText(),discount.getText());
        }else{ 
            programs.addSpecialization(name.getText(),discount.getText(),courses.getText());
        }
    }

    private void actionSearch(){
        String patronBusqueda = textSearch.getText();
        StringBuffer buffer = new StringBuffer();
        if(patronBusqueda.length() > 0) {
            ArrayList <Program> results = programs.search(patronBusqueda);
            for(int i = 0; i < results.size(); i++) {
                buffer.append(results.get(i).toString());
                buffer.append('\n');
                buffer.append('\n');
            }
        }
        textResults.setText(buffer.toString());
    } 

    public static void main(String args[]){
        IEMOISGUI gui=new IEMOISGUI();
        gui.setVisible(true);
    }    
}
