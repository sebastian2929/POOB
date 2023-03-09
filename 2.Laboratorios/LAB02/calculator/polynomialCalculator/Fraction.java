
/**
  *Fraction
  * This class implements the Fraction data type; that is, a rational number that can be written in the form p/q, where p and q are integers, with q <> 0
  * The implementation is done by immutable objects
  * INV: The fractional is represented irreducibly.
  * @author ESCUELA
  */
public class Fraction {

    
     /**Calculate the greatest common divisor of two integers
     * We will implement it using the recursive algorithm
     * @param a first integer
     * @param b second integer
     * @return the Greatest Common Divisor of a and b
     */
    public static int gcd(int a,int b){

        return 0;
    }    
    
    /**Create a new fraction, given the numerator and denominator
     * @param numerator
     * @param denominator. denominator <> 0
     */
    public Fraction (int numerator, int denominator) {

    }
    
    /**Create a fraction corresponding to an integer
     * @param integer the integer to create
     */
    public Fraction (int integer) {
    }

    /**Create a fraction, from its mixed representation.
     * The number created is mixedInteger + mixednumerator/mixeddenominator
     * @param integer the integer part of the number
     * @param numerator the numerator of the fractional part
     * @param denominator the denominator of the fractional part. denominator!=0
     */
    public Fraction (int integer, int numerator, int denominator) {

    }

    /**
     * Return the numerator of the simplified fraction
     * A fractional p/q is written in simplified form if q is a positive integer and
     * The greatest common divisor of p and q is 1.
     * @return The numerator of the simplified fraction
     */
    public int numerator() {
        return 0;
    }
    
     /**
     * Return the denominator of the simplified fraction
     * A fractional p/q is written in simplified form if q is a positive integer and
     * The greatest common divisor of p and q is 1.
     * @return The denominator of the simplified fraction
     */   
    public int denominator() {
        return 0;
    }
    
    /**
     * Add this fraction with another fraction
     * @param other is another fractional
     * @return this+other
     */
    public Fraction sume (Fraction other) {
        return null;
    }
    
    /**
     * Substract this fraction with another fraction
     * @param other is another fractional
     * @return this-other
     */
    public Fraction substract (Fraction other) {
        return null;
    }   
    /**
     * Multiply this fraction with another fraction
     * @param other is another fractional
     * @return this*other
     */
    public Fraction multiply (Fraction other) {
        return null;
    }
    
    
    /**Divide this fraction with another fraction
     * @param other is another fractional
     * @return this/other
     */
    public Fraction divide (Fraction other) {
        return null;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        return equals((Fraction)obj);
    }    
    
      /**Compare this fraction to another fraction
      * @param other eL other fractional
      * @return true if this fraction is mathematically equal to the other fraction, False d.l.c.
      */
    public boolean equals (Fraction other) {
        return false;
    }


    /** Calculate the string representation of a fraction in mixed simplified format
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString() {
        return "";
    }
    
}
