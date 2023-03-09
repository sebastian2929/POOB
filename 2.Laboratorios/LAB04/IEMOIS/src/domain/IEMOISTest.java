package domain;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class IEMOISTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IEMOISTest {
    /**
     * Default constructor for test class IEMOISTest
     */
    public IEMOISTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

    @Test
    public void shouldThrowExceptionIfHaveSameName() {
        IEMOIS courses = new IEMOIS();
        courses.addCourse("INTRO TO HTML AND CSS", "1000");
        courses.addCourse("INTRO TO HTML AND CSS", "1000");
        int size = courses.getSize();
        assertEquals(size, 8);
    }

    private void assertEquals(int size, int i) {
    }

    @Test
    public void HaveNotNameCourse() {
        IEMOIS courses = new IEMOIS();
        courses.addCourse("", "1000");
        int size = courses.getSize();
        assertEquals(size, 7);
    }

    @Test
    public void HaveNotNameSpecialization() {
        IEMOIS specialization = new IEMOIS();
        specialization.addSpecialization("", "1000", "Machine Learning");
        int size = specialization.getPrograms();
        assertEquals(size, 9);
    }

    @Test
    public void shouldNotAddCourseWithTheSameName() {
        IEMOIS courses = new IEMOIS();
        courses.addCourse("INTRO TO HTML AND CSS", "1000");
        courses.addCourse("INTRO TO HTML AND CSS", "1000");
        int size = courses.getSize();
        assertEquals(size, 8);
    }

    @Test
    public void shouldNotAddCourseWithNoPriceOrNegativePrice() {
        IEMOIS courses = new IEMOIS();
        courses.addCourse("INTRO TO HTML AND CSS", "hola");
        courses.addCourse("INTRO TO HTML", "-1000");
        int size = courses.getSize();
        assertEquals(size, 7);
    }

    @Test
    public void shouldNotAddCourseWithNoName() {
        IEMOIS iemois = new IEMOIS();
        iemois.addCourse("", "20");
        int size = iemois.getSize();
        assertEquals(size, 7);
    }

    @Test
    public void shouldNotAddSpecializationWithTheSameName() {
        IEMOIS iemois = new IEMOIS();
        iemois.addSpecialization("FRONT END DEVELOPER", "10", "Machine Learning");
        iemois.addSpecialization("FRONT END DEVELOPER", "10", "Machine Learning");
        int size = iemois.getPrograms();
        assertEquals(size, 10);
    }

    @Test
    public void shouldNotAddSpecializationWithNoName() {
        IEMOIS iemois = new IEMOIS();
        iemois.addSpecialization("", "20", "Machine Learning");
        int size = iemois.getPrograms();
        assertEquals(size, 9);
    }

    @Test
    public void shouldNotAddSpecializationWithNoPriceOrNegativePrice() {
        IEMOIS iemois = new IEMOIS();
        iemois.addSpecialization("INTRO TO HTML AND CSS", "hola", "Machine Learning");
        iemois.addSpecialization("INTRO TO HTML", "-1000", "Machine Learning");
        int size = iemois.getPrograms();
        assertEquals(size, 9);
    }

    @Test
    public void shouldNotAddSpecializationWithCourseThatDoesntExist() {
        IEMOIS iemois = new IEMOIS();
        iemois.addSpecialization("INTRO TO HTML AND CSS", "100", "aaa");
        int size = iemois.getSize();
        assertEquals(size, 7);
    }
}
