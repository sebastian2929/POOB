package icpc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ICPCTest.
 *
 * @author  Andres Felipe Arias - SebastianDavid Blanco
 * @version 1/11/2022
 */
public class ICPCTest
{
    /**
     * Default constructor for test class ICPCTest
     */
    public ICPCTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    // Metodo annadir interseccion.
    @Test
    public void shouldNotCreateIntersectionWithTheSameColor(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("black",45,45);
        icpc.addIntersection("black",50,50);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldCreateIntersectionWithDifferentColor(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("black",45,45);
        icpc.addIntersection("red",50,50);
        boolean bandera = icpc.ok();
        assertEquals(bandera,true);
    }
    
    @Test
    public void shouldNotCreateIntersectionWithTheSamePosition(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",45,45);
        icpc.addIntersection("black",45,45);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotAddIntersectionWithNotCorrectType(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("Stop","blue",351,65);
        icpc.addIntersection("roundabout","blue",375,78);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    //Metodo annadir ruta.
    @Test
    public void shouldNotCreateRouteWithNotInterseccion(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",35,300);
        icpc.addRoute("blue","red");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotCreateRouteWithSameInterseccion(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",35,300);
        icpc.addIntersection("blue",35,300);
        icpc.addRoute("blue","blue");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotCreateRouteIfExistRouteWithIntersections(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",35,300);
        icpc.addIntersection("red",78,321);
        icpc.addRoute("blue","red");
        icpc.addRoute("red","blue");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotCreateRouteIfExistHermitIntersectionWithRoute(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("hermit","blue",35,300);
        icpc.addIntersection("red",78,321);
        icpc.addIntersection("green",46,154);
        icpc.addRoute("blue","red");
        icpc.addRoute("gree","blue");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotCreateRouteWithNotCorrectType(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",35,300);
        icpc.addIntersection("red",78,321);
        icpc.addRoute("fast","blue","red");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    //Metodo annadir limite de velocidad a ruta.
    @Test
    public void shouldNotExistRouteSpeedLimitWithSameIntersection(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",400,400);
        icpc.addIntersection("red",200,200);
        icpc.addRoute("red","blue");
        icpc.routeSpeedLimit("blue","blue",40);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotExistRouteSpeedLimitWithNegativeSpeed(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",400,400);
        icpc.addIntersection("red",500,500);
        icpc.addRoute("blue","red");
        icpc.routeSpeedLimit("blue","red",-10);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotExistRouteSpeedLimitWithZero(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",400,400);
        icpc.addIntersection("red",500,500);
        icpc.addRoute("red","blue");
        icpc.routeSpeedLimit("blue","red",0);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }

    //Metodo annadir sennal.
    @Test
    public void shouldNotputSignIfNotRoute(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",35,300);
        icpc.addIntersection("red",78,321);
        icpc.putSign("red","blue",10);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotputSignIfExistSign(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",37,300);
        icpc.addIntersection("red",78,321);
        icpc.addRoute("blue","red");
        icpc.putSign("red","blue",10);
        icpc.putSign("blue","red",10);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotputSignIfExistRebelRoute(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",37,300);
        icpc.addIntersection("red",78,321);
        icpc.addRoute("rebel","blue","red");
        icpc.putSign("red","blue",10);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotputSignIfSpeedLimitIsNegative(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",37,300);
        icpc.addIntersection("red",78,321);
        icpc.addRoute("blue","red");
        icpc.putSign("red","blue",-10);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotPutSignWithNotCorrectType(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",300,500);
        icpc.addIntersection("red",200,600);
        icpc.addRoute("blue","red");
        icpc.putSign("Stop","red","blue",20);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    //Metodo borrar interseccion.
    @Test
    public void shouldNotDellIntersectionWithNotInterseccion(){
        ICPC icpc = new ICPC(800,800);
        icpc.delIntersection("blue");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
        @Test
    public void shouldNotDellIntersectionIfInterseccionIsFixed(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("fixed","red",200,400);
        icpc.delIntersection("red");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    //Metodo borrar ruta.
    @Test
    public void shouldNotDeleteRouteWithSameIntersections(){
        ICPC icpc = new ICPC(800,800);
        icpc.delRoad("blue","blue");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotDeleteRouteIfNotRoute(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",300,500);
        icpc.addIntersection("red",200,600);
        icpc.delRoad("blue","red");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotDeleteRouteIfFixedRoute(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",300,500);
        icpc.addIntersection("red",200,600);
        icpc.addRoute("fixed","blue","red");
        icpc.delRoad("blue","red");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    //Metodo borrar sennal
    @Test
    public void shouldNotDeleteSignIfSameIntersections(){
        ICPC icpc = new ICPC(800,800);
        icpc.removeSign("blue","blue");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }
    
    @Test
    public void shouldNotDeleteSignIfNotSign(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",300,500);
        icpc.addIntersection("red",200,600);
        icpc.addRoute("blue","red");
        icpc.removeSign("blue","blue");
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }

}
