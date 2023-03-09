package shapes;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import java.util.*;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6 (shapes)
 */
public class Canvas{

    private static Canvas canvasSingleton;

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Canvas getCanvas(String name,int height, int width){
        Color col = new Color(255,255,255);
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas(name, height, width, 
                col);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    //  ----- instance part -----

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColour;
    private Image canvasImage;
    private List <Object> objects;
    private HashMap <Object,ShapeDescription> shapes;

    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    public Canvas(String title, int width, int height, Color bgColour){
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColour = bgColour;
        frame.pack();
        objects = new ArrayList <Object>();
        shapes = new HashMap <Object,ShapeDescription>();
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible){
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColour);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  referenceObject  an object to define identity for this shape
     * @param  color            the color of the shape
     * @param  shape            the shape object to be drawn on the canvas
     */
    // Note: this is a slightly backwards way of maintaining the shape
    // objects. It is carefully designed to keep the visible shape interfaces
    // in this project clean and simple for educational purposes.
    public void draw(Object referenceObject, String color, Shape shape, int angle, int x, int y){
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);      // add at the end
        shapes.put(referenceObject, new ShapeDescription(shape, color, angle, x, y));
        redraw();
    }

    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(Object referenceObject){
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  newColour   the new colour for the foreground of the Canvas 
     */
    public void setForegroundColor(String colorStrings){
        HashMap<Integer,Color> colorString = new HashMap<Integer,Color>();
        Integer number = Integer.parseInt(colorStrings);
        Color black = new Color (0, 0, 0);
        colorString.put(0,black);
        Color red = new Color (255, 0, 0);
        colorString.put(1,red);
        Color blue = new Color (0, 0, 255);
        colorString.put(2,blue);
        Color yellow = new Color (255, 255, 0);
        colorString.put(3,yellow);
        Color green = new Color (0, 255, 0);
        colorString.put(4,green);
        Color magenta = new Color (255, 0, 255);
        colorString.put(5,magenta);
        Color cyan = new Color (0, 255, 255);
        colorString.put(6,cyan);
        Color grey = new Color (133, 133, 133);
        colorString.put(7,grey);
        Color orange = new Color (255, 133, 0);
        colorString.put(8,orange);
        for(int r = 55; r <= 255 ; r++){
            Color col = new Color (r, 0, 0);
            Integer key = 9 + r;
            colorString.put(r,col);              
        }
        for(int g = 55; g <= 255 ; g++){
            Color col = new Color (0, g, 0);
            Integer key = 210 + g;
            colorString.put(key,col);              
        }
        for(int b = 55; b <= 255 ; b++){
            Color col = new Color (0, 0, b);
            Integer key = 411 + b;
            colorString.put(key,col);     
        }
        Color colores = colorString.get(number);
        graphic.setColor(colores);
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (Exception e){
            // ignoring exception at the moment
        }
    }

    /**
     * Redraw ell shapes currently on the Canvas.
     */
    private void redraw(){
        erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
            shapes.get(i.next()).draw(graphic);
        }
        canvas.repaint();
    }

    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase(){
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        Dimension size = canvas.getSize();
        graphic.fill(new java.awt.Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }

    public void redraw1(){
        erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
            shapes.get(i.next()).draw(graphic);
        }
        canvas.repaint();
    }

    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    public class CanvasPane extends JPanel{
        public void paint(Graphics g){
            g.drawImage(canvasImage, 0, 0, null);
        }
    }

    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription{
        private Shape shape;
        private String colorString;
        private int angle;
        private int x;
        private int y;

        public ShapeDescription(Shape shape, String color, int angle, int x, int y){
            this.shape = shape;
            colorString = color;
            this.angle = angle;
            this.x = x;
            this.y = y;
        }   

        public void draw(Graphics2D graphic){
            AffineTransform old = graphic.getTransform();
            graphic.rotate(-Math.toRadians(angle),x,y);
            setForegroundColor(colorString);
            graphic.draw(shape);
            graphic.fill(shape);
            graphic.setTransform(old);
        }
    }
}