import java.util.HashMap;

/** PolynomialCalculator.java
 * 
 * @author ESCUELA 2022-02
 */
    
public class PolynomialCalculator{
    
    private HashMap<String,Polynomial> variables;
    
    public PolynomialCalculator(){
    }

    //Create a new variable
    public void create(String nombre){
    }
     
    //Assign a polynomial to an existing variable
    //a := polynomial fractions[0][0]/fractions[0][1]+ (fractions[1][0]/fractions[1][1]* x + ....
    public void assign(String set, int fractions[][] ){
    }    
    
    //Assigns the value of an operation to a variable
    // a = b op c
    //The operator characters are:  + - * /
    public void assign(String a, String b, char op, String c){
    }
  
    //Assigns the value of an operation to a variable
    // a = op b
    //The operator characters are:  d (derive) o i (integrate)
    public void assign(String a, char op, String b){
    }    
    
    //Evaluate the polynomial
    public String evaluate(String a, int [] x){
        return null;
    } 
    
    //Returns the polynomial
    public String toString(String set){
        return null;
    }
    
    
    //If the last operation was successful
    public boolean ok(){
        return false;
    }
}
    



