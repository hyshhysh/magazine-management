package customer.unittest;

import customer.AssociateCustomer;
import customer.PayingCustomer;
import magazine.Magazine;
import magazine.Supplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PayingCustomerTest {

    private PayingCustomer payingCustomer;
    private Magazine magazine;
    private Supplement supplement1;
    private Supplement supplement2;
    private AssociateCustomer associateCustomer;

    @BeforeEach
    void setUp() {
        // CreateMagazineView a Magazine instance for testing
        magazine = new Magazine("Grand Line Gazette", 15.0);

        // CreateMagazineView Supplement instances for testing
        supplement1 = new Supplement("Guide to Devil Fruits", 3.5);
        supplement2 = new Supplement("Haki Mastery", 5.5);

        // CreateMagazineView AssociateCustomer instance for testing
        associateCustomer = new AssociateCustomer("Associate", "Nico Robin",
                "114, Ohara, West Blue, 985899",
                "nicorobin@example.com", payingCustomer);

        // CreateMagazineView a PayingCustomer instance for testing
        payingCustomer = new PayingCustomer("Paying", "Franky Flam",
                "329, Water 7, South Blue, 322563",
                "frankyflam@example.com", "Credit Card", "4111-1111-1111-1111");
    }

    @Test
    void testConstructorWithoutSupplementsAndAssociates() {
        assertEquals("Franky Flam", payingCustomer.getName());
        assertEquals("Credit Card", payingCustomer.getPaymentMethod());
        assertEquals("4111-1111-1111-1111", payingCustomer.getPaymentDetails());
        assertTrue(payingCustomer.getAssociateCustomerList().isEmpty());
    }

    @Test
    void testAddAssociateCustomer() {
        payingCustomer.addAssociateCustomer(associateCustomer);
        assertEquals(1, payingCustomer.getAssociateCustomerList().size());
        assertEquals(associateCustomer, payingCustomer.getAssociateCustomerList().get(0));
    }

    @Test
    void testRemoveAssociateCustomer() {
        payingCustomer.addAssociateCustomer(associateCustomer);
        payingCustomer.removeAssociateCustomer(associateCustomer);
        assertTrue(payingCustomer.getAssociateCustomerList().isEmpty());
    }

    @Test
    void testGetWeeklyPaymentWithoutSupplementsOrAssociates() {
        double expectedWeeklyPayment = magazine.getWeeklyCost();
        assertEquals(expectedWeeklyPayment, payingCustomer.getWeeklyPayment(magazine));
    }

    @Test
    void testGetWeeklyPaymentWithSupplements() {
        ArrayList<Supplement> supplements = new ArrayList<>();
        supplements.add(supplement1);
        supplements.add(supplement2);
        payingCustomer.setSupplementList(supplements);

        double expectedWeeklyPayment = magazine.getWeeklyCost() + supplement1.getWeeklyCost() + supplement2.getWeeklyCost();
        assertEquals(expectedWeeklyPayment, payingCustomer.getWeeklyPayment(magazine));
    }

    @Test
    void testGetWeeklyPaymentWithAssociates() {
        payingCustomer.addAssociateCustomer(associateCustomer);
        double expectedWeeklyPayment = magazine.getWeeklyCost() + associateCustomer.getWeeklyPayment(magazine);
        assertEquals(expectedWeeklyPayment, payingCustomer.getWeeklyPayment(magazine));
    }

    @Test
    void testGetMonthlyPayment() {
        double expectedMonthlyPayment = payingCustomer.getWeeklyPayment(magazine) * 4;
        assertEquals(expectedMonthlyPayment, payingCustomer.getMonthlyPayment(magazine));
    }

    @Test
    void testGetBillingHistory() {
        payingCustomer.addAssociateCustomer(associateCustomer);
        payingCustomer.setSupplementList(new ArrayList<>(List.of(supplement1, supplement2)));

        String expectedBillingHistory = "---------------------------- INVOICE ---------------------------\n" +
                "\n" +
                "------------------- Cost Breakdown -------------------\n\n" +
                "Paying Customer: Franky Flam\n" +
                "Magazine Subscription:\n" +
                "     Grand Line Gazette - $15.0\n" +
                "\nSupplements:\n" +
                "     Guide to Devil Fruits - $3.5\n" +
                "     Haki Mastery - $5.5\n" +
                "\nAssociate Customers:" +
                "\n1. Nico Robin\n" +
                "Magazine Subscription:\n" +
                "     Grand Line Gazette - $15.0\n" +
                "     No supplements.\n" +
                "------------------------------------------------------------------\n" +
                "Total weekly cost: $" + payingCustomer.getWeeklyPayment(magazine) +
                "\nTotal payment due for the month: $" + payingCustomer.getMonthlyPayment(magazine) +
                "\n\nThank you for your subscription!" +
                "\n------------------------------------------------------------------";

        assertEquals(expectedBillingHistory, payingCustomer.getBillingHistory(magazine));
    }

} // END OF PayingCustomerTest CLASS