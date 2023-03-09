package domain;
import java.awt.Color;

/*No olviden adicionar la documentacion*/
public interface Thing{
  int ROUND = 1;
  int SQUARE = 2;


  void decide();
   
  default void change(){
  };
  
  default int shape(){
      return SQUARE;
  }
  
  default Color getColor(){
      return Color.red;
  };
  
  default boolean isActive(){
      return false;
  }
  
  
     
}
