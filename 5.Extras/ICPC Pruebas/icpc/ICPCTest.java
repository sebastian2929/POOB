package icpc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ICPCTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
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
    public void shouldNotCreateIntersectionWithPositionBiggerThanCanvas(){
        ICPC icpc = new ICPC(800,800);
        icpc.addIntersection("blue",35,799);
        icpc.addIntersection("black",-10,45);
        boolean bandera = icpc.ok();
        assertEquals(bandera,false);
    }

    @Test
    public void shouldNotCreateRutaWithNotInterseccion(){

    }
    
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
}
