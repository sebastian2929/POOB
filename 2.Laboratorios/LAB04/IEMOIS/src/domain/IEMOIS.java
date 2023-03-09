package domain;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * IEMOIS
 * @author POOB  
 * @version ECI 2022
 */

public class IEMOIS{
    private LinkedList<Program> programs;
    private HashMap<String,Course> courses;

    /**
     * Create a IEMOIS
     */
    public IEMOIS(){
        programs = new LinkedList<Program>();
        courses = new HashMap<String,Course>();
        addSome();
    }

    private void addSome(){
        String [][] courses = {{"Aprendiendo a Aprender. MacMaster-California. Coursera","41"},
                {"Introduction to Computer Science and Programming Using Python","75"},
                {"Databases: Modeling and Theory","50"},{"Databases: Relational Databases and SQL","50"}, 
                {"Databases: Advances Topics in SQL","50"},{"Databases: Semistructured Data", "50"},
                {"Machine Learning","95"}};
        for (String [] c: courses){
            addCourse(c[0],c[1]);
        }
        String [][] specializations = {{"Developing Databases. Stanford Online. Edx.", "50", "Databases: Modeling and Theory\nDatabases: Relational Databases and SQL"},
                {"Advanced Topics of Databases. Standford Online. Edx.", "10", "Databases: Advances Topics in SQL\nDatabases: Semistructured Data"}};
        for (String [] s: specializations){
            addSpecialization(s[0],s[1],s[2]);
        }
    }

    /**
     * Consult a program
     * @param name
     * @return 
     */
    public Program consult(String name){
        Program p=null;
        for(int i=0;i<programs.size() && p == null;i++){
            if (programs.get(i).name().compareToIgnoreCase(name)==0) 
                p=programs.get(i);
        }
        return p;
    }

    /**
     * Add a new course
     * @param name 
     * @param price
     */
    public void addCourse(String name, String price){
        boolean bandera = numberString(price); 
        if(!courses.containsKey(name.toUpperCase()) && bandera) {
            Integer precio = Integer.parseInt(price);
            if(precio != null && precio > 0 && !name.equals("")){
                Course nc=new Course(name,Integer.parseInt(price));
                programs.add(nc);
                courses.put(name.toUpperCase(),nc);
            }
        }
    }

    /**
     * Add a new specialization
     * @param name 
     * @param discount
     * @param courses
     */
    public void addSpecialization(String name, String discount, String courses){ 
        boolean bandera2 = numberString(discount);
        boolean bandera = verificaS(name);
        if(!name.equals("") && !bandera && bandera2){
            Integer Precio = Integer.parseInt(discount);
            if(Precio !=  null && Precio > 0){
                Specialization s = new Specialization(name,Integer.parseInt(discount));
                String [] aCourses= courses.split("\n");
                for (String p : aCourses){
                    Course course = this.courses.get(p.toUpperCase());
                    if(course != null){
                    s.addCourse(course);
                    }
                }
                programs.add(s);
            }
        }
    }

    /**
     * Consults the programs that start with a prefix
     * @param  
     * @return 
     */
    public LinkedList<Program> select(String prefix){
        LinkedList <Program> answers=null;
        prefix=prefix.toUpperCase();
        for(int i=0;i<programs.size();i++){
            if(programs.get(i).name().toUpperCase().startsWith(prefix)){
                answers.add(programs.get(i));
            }   
        }
        return answers;
    }

    /**
     * Consult selected programs
     * @param selected
     * @return  
     */
    public String data(LinkedList<Program> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(programs.size()+ " programas\n");
        for(Program p : programs) {
            try{
                answer.append(p.data());
                answer.append("\n");
            }catch(IEMOISException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }

    /**
     * Return the data of programs with a prefix
     * @param prefix
     * @return  
     */ 
    public ArrayList<Program> search(String prefix){
        ArrayList<Program> programas = new ArrayList<Program>();
        for(Program p: programs){
            if(p.name().contains(prefix)){
               programas.add(p);
            }   
        }
        return programas;
    }

    /**
     * Return the data of all programs
     * @return  
     */    
    public String toString(){
        return data(programs);
    }

    /**
     * Consult the number of Programs
     * @return 
     */
    public int numberPrograms(){
        return programs.size();
    }

    /*
    asd   
     */
    public int getSize(){
        return courses.size();
    }
    
    public int getPrograms(){
        return programs.size();
    }

    private boolean verificaS(String name){
        boolean bandera = false;
        for(Program specializaction: programs){
            String nameS = specializaction.name();         
            if(nameS.equals(name)){
                bandera = true;
            }
        }
        return bandera;
    }
    
    public boolean numberString(String numero){
        boolean isNumeric = (numero != null && numero.matches("[0-9]+"));
	return isNumeric;
    }
}