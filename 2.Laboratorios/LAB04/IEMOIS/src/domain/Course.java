package domain; 

public class Course extends Program{

    private Integer price;

    public Course(String name, Integer price){
        this.name=name;
        this.price=price;

    }    

    @Override
    public int price() throws IEMOISException{
        if(price == null) throw new IEMOISException(IEMOISException.COURSE_NO_PRICE);
        if (price < 1) throw new IEMOISException(IEMOISException.COURSE_ERROR_PRICE);
        return price;
    }

    public String data(){
        return name+". Precio:" +price;
    }
    
    public String getName(){
        return name;
    }
}
