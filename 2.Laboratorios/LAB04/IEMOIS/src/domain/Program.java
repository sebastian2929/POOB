package domain;



public abstract class Program{
    
    protected String name;

    /**
     * Return the name
     * @return
     */
    public String name(){
        return name;
    }

    /**
     * Return the price
     * @return
     * @throws IEMOISException, if the price is not available
     */
    public abstract int price() throws IEMOISException;
    
    /**
     * Return the representation as string
     * @return
     * @throws IEMOISException, if the data is not complete
     */    
    public abstract String data() throws IEMOISException;

}
