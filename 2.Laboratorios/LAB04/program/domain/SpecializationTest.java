package domain;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SpecializationTest{
   
    
    public SpecializationTest(){
    }


    @Before
    public void setUp(){
        
    }

    @After
    public void tearDown(){
    }
    
    @Test
    public void shouldCalculateTheCostOfASpecialization(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 10);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        s.addCourse(new Course("INTRO TO JAVASCRIPT",8000));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", 1000));
        try {
           assertEquals(9000,s.price());
        } catch (IEMOISException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldThrowExceptionIfSpecializationHasNoCourses(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 10);
        try { 
           int price=s.price();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.SPECIALIZATION_EMPTY,e.getMessage());
        }    
    }    
    
   @Test
    public void shouldThrowExceptionIfThereIsErrorInPrice(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 10);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        s.addCourse(new Course("INTRO TO JAVASCRIPT",8000));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", -1000));
        try { 
           int price=s.price();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.COURSE_ERROR_PRICE,e.getMessage());
        }    
    }     
    
   @Test
    public void shouldThrowExceptionIfPriceIsNotKnown(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 10);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        s.addCourse(new Course("INTRO TO JAVASCRIPT",null));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", -1000));
        try { 
           int price=s.price();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.COURSE_NO_PRICE,e.getMessage());
        }    
    }  
    
}
