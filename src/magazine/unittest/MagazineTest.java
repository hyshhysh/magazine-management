package magazine.unittest;

import magazine.Magazine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagazineTest {

    private Magazine magazine;

    @BeforeEach
    public void setUp() {
        // Initialize a new Magazine object before each test
        magazine = new Magazine("Grand Line Gazette", 15.0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Grand Line Gazette", magazine.getName());
        assertEquals(15.0, magazine.getWeeklyCost());
    }

    @Test
    public void testGetName() {
        assertEquals("Grand Line Gazette", magazine.getName());
    }

    @Test
    public void testSetName() {
        magazine.setName("New Magazine Name");
        assertEquals("New Magazine Name", magazine.getName());
    }

    @Test
    public void testGetWeeklyCost() {
        assertEquals(15.0, magazine.getWeeklyCost());
    }

    @Test
    public void testSetWeeklyCost() {
        magazine.setWeeklyCost(20.0);
        assertEquals(20.0, magazine.getWeeklyCost());
    }


} // END OF MagazineTest CLASS
