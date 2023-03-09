package domain; 

import java.util.ArrayList;

public class Specialization extends Program{
    public final static int DEFAULT_PRICE=10000;

    private int discount;
    private ArrayList<Course> courses;

    /**
     * Constructs a new specializacion
     * @param name 
     * @param discount 
     */
    public Specialization(String name, int discount){
        this.name=name;
        this.discount = discount;
        courses = new ArrayList<Course>();
    }

    /**
     * Add a new course
     * @param c
     */   
    public void addCourse(Course c){
        courses.add(c);
    }

    @Override
    public String data() throws IEMOISException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+". Descuento: "+ discount);
        answer.append('\n');
        for(Course c: courses) {
            answer.append("\t"+c.name()+ " : " + c.price());
            answer.append('\n');
        }
        return answer.toString();
    }    

    @Override
    /**
     * Return the price
     * @return
     * @throws IEMOISException, if the price is not available
     */
    public int price() throws IEMOISException{
        Integer price = 0;
        int descuento = 0;
        if (courses.size()==0){ 
            throw new IEMOISException(IEMOISException.SPECIALIZATION_EMPTY);
        }
        for(Course c : courses){
            price += c.price();
        }
        descuento = (price * discount)/100;
        price -= descuento;
        return price;
    }

    /**
     * Calculates the default price of a Specialization.
     * For courses where the price cannot be known, the DEFAULT_PRICE is assumed. 
     * @return 
     * @throws IEMOISException SPECIALIZATION_EMPTY, if it doesn't have courses
     */
    public int defaultPrice() throws IEMOISException{
        int price = 0;
        try{
            if(courses.size()==0)throw new IEMOISException(IEMOISException.SPECIALIZATION_EMPTY);
            for(Course c : courses){
                price += c.price();
            }
        }
        catch(IEMOISException e){
            e.getMessage();
        }
        return price;
    }   
    
    
    public int price(String name) throws IEMOISException{
        int price = 0;
        int indice = 0;
        if(courses.size()==0) throw new IEMOISException(IEMOISException.SPECIALIZATION_EMPTY);
        for(Course c: courses){
            String nameCourse = c.getName();
            if(nameCourse.equals(name)){
                indice += 1;
                price = c.price();
            } 
        }
        if (indice == 0) throw new IEMOISException(IEMOISException.COURSE_ERROR_NAME);
        if (indice > 1) throw new IEMOISException(IEMOISException.COURSE_SAME_NAME);
        return price;
    }  
}
