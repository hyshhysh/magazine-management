package customer.unittest;

import customer.AssociateCustomer;
import customer.PayingCustomer;
import magazine.Magazine;
import magazine.Supplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssociateCustomerTest {

    private AssociateCustomer associateCustomer;
    private PayingCustomer payingCustomer;
    private Magazine magazine;
    private Supplement supplement1;
    private Supplement supplement2;

    @BeforeEach
    void setUp() {
        // CreateMagazineView a Magazine instance for testing
        magazine = new Magazine("Grand Line Gazette", 15.0);

        // CreateMagazineView Supplement instances for testing
        supplement1 = new Supplement("Navigator's Almanac", 3.5);
        supplement2 = new Supplement("Shipwright’s Showcase", 4.0);

        // CreateMagazineView a PayingCustomer instance for testing
        payingCustomer = new PayingCustomer("Paying", "Boa Hancock",
                "516, Amazon Lily, Calm Belt, 421314",
                "boahancock@example.com", "Credit Card", "4111-1111-1111");

        // CreateMagazineView an AssociateCustomer instance for testing
        associateCustomer = new AssociateCustomer("Associate", "Trafalgar Law",
                "660, Flavence, North Blue, 267244", "john@example.com", payingCustomer);
    }

    @Test
    void testConstructorWithoutSupplements() {
        assertEquals("Trafalgar Law", associateCustomer.getName());
        assertEquals("john@example.com", associateCustomer.getEmailAdd());
        assertEquals(payingCustomer, associateCustomer.getPayingCustomer());
        assertNull(associateCustomer.getSupplementList());
    }

    @Test
    void testConstructorWithSupplements() {
        ArrayList<Supplement> supplements = new ArrayList<>();
        supplements.add(supplement1);
        supplements.add(supplement2);

        associateCustomer = new AssociateCustomer("Associate", "Trafalgar Law",
                "660, Flavence, North Blue, 267244", "john@example.com", supplements, payingCustomer);

        assertEquals(2, associateCustomer.getSupplementList().size());
        assertEquals(supplement1, associateCustomer.getSupplementList().get(0));
        assertEquals(supplement2, associateCustomer.getSupplementList().get(1));
    }

    @Test
    void testGetWeeklyPaymentWithoutSupplements() {
        double expectedWeeklyPayment = magazine.getWeeklyCost();
        assertEquals(expectedWeeklyPayment, associateCustomer.getWeeklyPayment(magazine));
    }

    @Test
    void testGetWeeklyPaymentWithSupplements() {
        ArrayList<Supplement> supplements = new ArrayList<>();
        supplements.add(supplement1);
        supplements.add(supplement2);
        associateCustomer.setSupplementList(supplements);

        double expectedWeeklyPayment = magazine.getWeeklyCost() + supplement1.getWeeklyCost() + supplement2.getWeeklyCost();
        assertEquals(expectedWeeklyPayment, associateCustomer.getWeeklyPayment(magazine));
    }

    @Test
    void testGetMonthlyPayment() {
        associateCustomer.setSupplementList(new ArrayList<>(List.of(supplement1, supplement2)));
        double expectedMonthlyPayment = associateCustomer.getWeeklyPayment(magazine) * 4;
        assertEquals(expectedMonthlyPayment, associateCustomer.getMonthlyPayment(magazine));
    }

    @Test
    void testGetPayingCustomer() {
        assertEquals(payingCustomer, associateCustomer.getPayingCustomer());
    }

    @Test
    void testSetPayingCustomer() {
        PayingCustomer newPayingCustomer = new PayingCustomer("Paying", "Nami",
                "123, Weatheria, Sky Island, 123456", "nami@example.com", "Debit Card", "5111-1111-1111");
        associateCustomer.setPayingCustomer(newPayingCustomer);
        assertEquals(newPayingCustomer, associateCustomer.getPayingCustomer());
    }

    @Test
    void testGetEmailAdd() {
        assertEquals("john@example.com", associateCustomer.getEmailAdd());
    }


} // END OF AssociateCustomerTest CLASS
