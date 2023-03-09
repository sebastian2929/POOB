
/**
  *Fraction
  * This class implements the Fraction data type; that is, a rational number that can be written in the form p/q, where p and q are integers, with q <> 0
  * The implementation is done by immutable objects
  * INV: The fractional is represented irreducibly.
  * @author ESCUELA
  */
public class Fraction {
    private int numerator;
    private int denominator; 

    /**Calculate the greatest common divisor of two integers
     * We will implement it using the recursive algorithm
     * @param a first integer
     * @param b second integer
     * @return the Greatest Common Divisor of a and b
     */
    public static int gcd(int a,int b){
        int temporal;
        while(b!= 0){
            temporal = b;
            b = a % b;
            a = temporal;
        }
        return Math.abs(a);
    }    
    
    /**Create a new fraction, given the numerator and denominator
     * @param numerator
     * @param denominator. denominator <> 0
     */
    public Fraction (int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    /**Create a fraction corresponding to an integer
     * @param integer the integer to create
     */
    public Fraction (int integer) {
      this.numerator = integer * 5;
      this.denominator = 5;
    }

    /**Create a fraction, from its mixed representation.
     * The number created is mixedInteger + mixednumerator/mixeddenominator
     * @param integer the integer part of the number
     * @param numerator the numerator of the fractional part
     * @param denominator the denominator of the fractional part. denominator!=0
     */
    public Fraction (int integer, int numerator, int denominator) {
        this.numerator = (integer * denominator) + numerator;
        this.denominator = denominator;
    }

    /**
     * Return the numerator of the simplified fraction
     * A fractional p/q is written in simplified form if q is a positive integer and
     * The greatest common divisor of p and q is 1.
     * @return The numerator of the simplified fraction
     */
    public int numerator() {
        Simplify(this.numerator,this.denominator);
        if(this.denominator < 0){
            if(this.numerator < 0){
                this.denominator = Math.abs(denominator);
                this.numerator = Math.abs(numerator);
            }
        }
        if(this.denominator < 0){
            if(this.numerator >= 0){
                this.denominator = Math.abs(denominator);
                this.numerator = numerator * -1;
            }
        }
        return this.numerator;
    }
    
     /**
     * Return the denominator of the simplified fraction
     * A fractional p/q is written in simplified form if q is a positive integer and
     * The greatest common divisor of p and q is 1.
     * @return The denominator of the simplified fraction
     */   
    public int denominator() {
        Simplify(this.numerator,this.denominator);
        if(this.denominator < 0){
            if(this.numerator < 0){
                this.denominator = Math.abs(denominator);
                this.numerator = Math.abs(numerator);
            }
        }
        if(this.denominator < 0){
            if(this.numerator >= 0){
                this.denominator = Math.abs(denominator);
                this.numerator = numerator * -1;
            }
        }
        return this.denominator;
    }
    
    /**
     * Add this fraction with another fraction
     * @param other is another fractional
     * @return this+other
     */
    public Fraction sume (Fraction other) {
        Fraction nuevaf = new Fraction((this.numerator*other.denominator) + (this.denominator * other.numerator),(this.denominator*other.denominator));
        return nuevaf;
    }
    
    /**
     * Substract this fraction with another fraction
     * @param other is another fractional
     * @return this-other
     */
    public Fraction substract (Fraction other) {
        Fraction nuevaf = new Fraction((this.numerator*other.denominator) - (this.denominator * other.numerator),(this.denominator*other.denominator));
        return nuevaf;
    }   
    
    /**
     * Multiply this fraction with another fraction
     * @param other is another fractional
     * @return this*other
     */
    public Fraction multiply (Fraction other) {
        Fraction nuevaf = new Fraction((this.numerator*other.numerator),(this.denominator*other.denominator));
        return nuevaf;
    }
    
    
    /**Divide this fraction with another fraction
     * @param other is another fractional
     * @return this/other
     */
    public Fraction divide (Fraction other) {
        Fraction nuevaf = new Fraction((this.numerator*other.denominator),(this.denominator*other.numerator));
        return nuevaf;
    }
    
    @Override
    public boolean equals(Object obj) {
        return equals((Fraction)obj);
    }    
    
      /**Compare this fraction to another fraction
      * @param other eL other fractional
      * @return true if this fraction is mathematically equal to the other fraction, False d.l.c.
      */
    public boolean equals (Fraction other){
        Fraction nuevaf = new Fraction((this.numerator),(this.denominator));
        nuevaf.Simplify(this.numerator,this.denominator);
        other.Simplify(other.numerator,other.denominator);
        if(nuevaf.numerator == 0 && other.numerator == 0){
            return true;
        }        
        if(Math.abs(nuevaf.numerator) == Math.abs(other.numerator) && Math.abs(nuevaf.denominator) == Math.abs(other.denominator)){
            return true;
        } else {
            return false;
        }
    }

    /** Calculate the string representation of a fraction in mixed simplified format
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString() {
        Simplify(this.numerator,this.denominator);
        return numerator + "/" + denominator;
    }
    
    /**
     * Simplify a Fraction
     * @param numerator
     * @param denominator
     */
    public void Simplify(int numerador,int denominador){
        int n = gcd(numerator,denominator);
        numerator = numerator/n;
        denominator = denominator/n;
    }
}

