package icpc;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ICPCContestTest.
 *
 * @author  Andres Felipe Arias - Sebastian David Blanco
 * @version 1/11/2022
 */
public class ICPCContestTest
{
    /**
     * Default constructor for test class ICPCContestTest
     */
    public ICPCContestTest()
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
    public void shouldCreateTheSimulate(){
        ICPCContest icpcContest = new ICPCContest();
        int[][] matriz = {{1,2,10}};
        icpcContest.simulate(2,matriz);        
        boolean bandera = icpcContest.ok();
        assertEquals(bandera,true);
    }
    
    @Test
    public void shouldNotCreateTheSimulate(){
        ICPCContest icpcContest = new ICPCContest();
        int[][] matriz = {{11,2,10},{1,30,5},{1,4,7},{2,5,9}};
        icpcContest.simulate(-2,matriz);        
        boolean bandera = icpcContest.ok();
        assertEquals(false,bandera);
    }
    
    @Test
    public void shouldSolve(){
        ICPCContest icpcContest = new ICPCContest();
        int[][] matriz = {{1,2,10},{1,3,5},{1,4,7},{2,5,9}};
        int bandera = icpcContest.solve(2,matriz);        
        assertEquals(7,bandera);
    }
    
        @Test
    public void shouldNotSolve(){
        ICPCContest icpcContest = new ICPCContest();
        int[][] matriz = {{1,2,10},{1,3,5},{1,4,7},{2,5,9}};
        icpcContest.solve(-2,matriz);
        boolean bandera = icpcContest.ok();
        assertEquals(bandera,false);
    }
}
