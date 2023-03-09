package domain;
import java.awt.Color;


/**
 * Write a description of class Semaforo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Semaforo extends Cell
{
    Color GREEN = Color.green;
    Color RED = Color.red;
    Color YELLOW = Color.yellow;
    
    /**
     * Constructor for objects of class Mimos
     */
    public Semaforo(AManufacturing am,int row, int column, boolean active){
        super(am,row, column,active);
        color = Color.red;
    }
    
    @Override
    public void decide() {
        // Cambiando de color segun un semaforo
        if (isActive()) {
            if (getColor()== Color.red) {
                color = YELLOW;
            }else if(getColor()== Color.yellow) {
                color = GREEN;
            }
            else{
                color = RED;
            }
        }
        if (getSteps() >= 10) {
            nextState = 'd';
        }
    }
    
    public int shape(){
          return Thing.ROUND;
    }
}
