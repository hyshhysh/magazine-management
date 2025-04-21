package magazine.unittest;

import magazine.Supplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplementTest {

    private Supplement supplement;

    @BeforeEach
    public void setUp() {
        supplement = new Supplement("Guide to Devil Fruits", 3.0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Guide to Devil Fruits", supplement.getName());
        assertEquals(3.0, supplement.getWeeklyCost());
    }

    @Test
    public void testGetName() {
        assertEquals("Guide to Devil Fruits", supplement.getName());
    }

    @Test
    public void testSetName() {
        supplement.setName("Haki Mastery");
        assertEquals("Haki Mastery", supplement.getName());
    }

    @Test
    public void testGetWeeklyCost() {
        assertEquals(3.0, supplement.getWeeklyCost());
    }

    @Test
    public void testSetWeeklyCost() {
        supplement.setWeeklyCost(4.5);
        assertEquals(4.5, supplement.getWeeklyCost());
    }

}
