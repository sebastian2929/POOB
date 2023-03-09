import java.util.HashMap;

/** PolynomialCalculator.java
 * 
 * @author ESCUELA 2022-02
 */
    
public class PolynomialCalculator{
    
    private HashMap<String,Polynomial> variables;
    private boolean okay;
    
    /**
     * Constructor de la calculadora
     */
    public PolynomialCalculator(){
        this.variables = new HashMap<String,Polynomial>();
    }
    
    public HashMap<String,Polynomial> getVariables(){
        return this.variables;
    }
    
    //Create a new variable
    /**
     * 
     * @param nombre Nombre de la variable
     */
    public void create(String nombre){
        this.variables.put(nombre,null);
    }
     
    //Assign a polynomial to an existing variable
    //a := polynomial fractions[0][0]/fractions[0][1]+ (fractions[1][0]/fractions[1][1]* x + ....
    public void assign(String set, int fractions[][] ){
        Polynomial newPolynomial = new Polynomial(int[][] fractions);
        this.variables.putIFAbsent(set,newPolynomial);
    }    
    
    //Assigns the value of an operation to a variable
    // a = b op c
    //The operator characters are:  + - * /
    public void assign(String a, String b, char op, String c){
        Polynomial polynomialA = new Polynomial();
        Polynomial polynomialB = new Polynomial();
        
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
        return this.okay;
    }
}
    