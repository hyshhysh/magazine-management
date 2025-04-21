package model.unitTest;

import customer.AssociateCustomer;
import customer.Customer;
import customer.PayingCustomer;
import magazine.Magazine;
import magazine.Supplement;
import model.MagazineModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MagazineModelTest {

    private MagazineModel magazineModel;
    private Magazine magazine;
    private PayingCustomer payingCustomer;
    private AssociateCustomer associateCustomer1;
    private AssociateCustomer associateCustomer2;
    private Supplement supplement1;
    private Supplement supplement2;

    @BeforeEach
    void setUp() {
        magazineModel = new MagazineModel();

        // CreateMagazineView a Magazine instance
        magazine = new Magazine("Grand Line Gazette", 15.0);
        magazineModel.setMagazine(magazine);

        // CreateMagazineView a PayingCustomer instance
        payingCustomer = new PayingCustomer("Paying", "Boa Hancock",
                "516, Amazon Lily, Calm Belt, 421314",
                "boahancock@op.com", "Credit Card", "4111-1111-1111");

        // CreateMagazineView AssociateCustomer instances
        associateCustomer1 = new AssociateCustomer("Associate", "Trafalgar Law", "660, Flavence, North Blue, 267244", "law@op.com", payingCustomer);
        associateCustomer2 = new AssociateCustomer("Associate", "Nami",
                "123, Weatheria, Sky Island, 123456", "nami@op.com", payingCustomer);

        // CreateMagazineView Supplement instances
        supplement1 = new Supplement("Guide to Devil Fruits", 3.0);
        supplement2 = new Supplement("Haki Mastery", 3.5);

        // Initialise the customers list
        magazineModel.setCustomers(new ArrayList<>());
        // Initialise the supplements list
        magazineModel.setSupplements(new ArrayList<>());

    }

    @Test
    void testSetMagazine() {
        magazineModel.setMagazine(magazine);
        assertEquals(magazine, magazineModel.getMagazine());
    }

    @Test
    void testSetFileName() {
        magazineModel.setFileName("customers.txt");
        assertEquals("customers.txt", magazineModel.getFileName());
    }

    @Test
    void testAddNewCustomer() {
        magazineModel.addNewCustomer(payingCustomer);
        assertTrue(magazineModel.getCustomers().contains(payingCustomer));
    }

    @Test
    void testDeleteCustomer() {
        magazineModel.addNewCustomer(payingCustomer);
        magazineModel.addNewCustomer(associateCustomer1);
        magazineModel.addNewCustomer(associateCustomer2);

        // Delete the paying customer
        magazineModel.deleteCustomer(payingCustomer);
        assertFalse(magazineModel.getCustomers().contains(payingCustomer));

        // Check if associate customers are removed
        assertTrue(magazineModel.getCustomers().contains(associateCustomer1));
        assertTrue(magazineModel.getCustomers().contains(associateCustomer2));

        // Delete associate customer
        magazineModel.deleteCustomer(associateCustomer1);
        assertFalse(magazineModel.getCustomers().contains(associateCustomer1));
    }

    @Test
    void testAddSupplement() {
        magazineModel.addNewSupplement(supplement1);
        assertTrue(magazineModel.getSupplements().contains(supplement1));
    }

    @Test
    void testDeleteSupplement() {
        magazineModel.addNewSupplement(supplement1);
        magazineModel.addNewSupplement(supplement2);

        // Delete supplement1
        magazineModel.deleteSupplement(supplement1);
        assertFalse(magazineModel.getSupplements().contains(supplement1));
        assertTrue(magazineModel.getSupplements().contains(supplement2));

        // Delete supplement2
        magazineModel.deleteSupplement(supplement2);
        assertTrue(magazineModel.getSupplements().isEmpty());
    }

    @Test
    void testGetSupplementViaName() {
        magazineModel.addNewSupplement(supplement1);
        magazineModel.addNewSupplement(supplement2);

        Supplement foundSupplement = magazineModel.getSupplementViaName("Guide to Devil Fruits");
        assertNotNull(foundSupplement);
        assertEquals(supplement1, foundSupplement);

        // Test for a non-existing supplement
        assertNull(magazineModel.getSupplementViaName("Fake Supplement"));

        // Test for case insensitivity
        foundSupplement = magazineModel.getSupplementViaName("haki mastery");
        assertNotNull(foundSupplement);
        assertEquals(supplement2, foundSupplement);
    }

    @Test
    void testGetCustomerViaEmail() {
        magazineModel.addNewCustomer(payingCustomer);
        magazineModel.addNewCustomer(associateCustomer1);

        Customer foundCustomer = magazineModel.getCustomerViaEmail("boahancock@op.com");
        assertNotNull(foundCustomer);
        assertEquals(payingCustomer, foundCustomer);

        // Test for a non-existing email
        assertNull(magazineModel.getCustomerViaEmail("fake@example.com"));

        // Test for case insensitivity
        foundCustomer = magazineModel.getCustomerViaEmail("LAW@OP.COM");
        assertNotNull(foundCustomer);
        assertEquals(associateCustomer1, foundCustomer);
    }

    @Test
    void testEditMagazineInfo() {
        // Test editing of magazine name
        magazineModel.editMagazineInfo("Pirate Times");
        assertEquals("Pirate Times", magazineModel.getMagazine().getName());

        // Test editing of magazine cost
        magazineModel.editMagazineInfo(18.0);
        assertEquals(18.0, magazineModel.getMagazine().getWeeklyCost());

        // Test editing of magazine name and cost
        magazineModel.editMagazineInfo("Nautical Almanac", 25.0);
        assertEquals("Nautical Almanac", magazineModel.getMagazine().getName());
        assertEquals(25.0, magazineModel.getMagazine().getWeeklyCost());
    }

    @Test
    void testEditCustomerInfo() {
        // Test editing of customer name
        magazineModel.editCustomerName(payingCustomer, "New Paying Customer Name");
        assertEquals("New Paying Customer Name", payingCustomer.getName());

        // Test editing of customer address
        String newAddress = "123, New Street, New City, 123456";
        magazineModel.editCustomerAddress(payingCustomer, newAddress);
        assertEquals(newAddress, payingCustomer.getAddress());

        // Test editing of customer email address
        magazineModel.editCustomerEmailAdd(payingCustomer, "newemail@example.com");
        assertEquals("newemail@example.com", payingCustomer.getEmailAdd());

        // Test editing of customer supplement list
        ArrayList<Supplement> newSupplements = new ArrayList<>();
        newSupplements.add(supplement1);
        newSupplements.add(supplement2);
        magazineModel.editCustomerSupplements(payingCustomer, newSupplements);
        assertEquals(newSupplements, payingCustomer.getSupplementList());
    }

    @Test
    void testEditPayingCustomerInfo() {
        // Test editing of Paying Customer payment method and details
        magazineModel.editPaymentMethod(payingCustomer, "Bank Account Debit", "564452155");
        assertEquals("Bank Account Debit", payingCustomer.getPaymentMethod());
        assertEquals("564452155", payingCustomer.getPaymentDetails());

        // Test editing of Paying Customer's associate customer list
        ArrayList<AssociateCustomer> newAssociateCustomers = new ArrayList<>();
        newAssociateCustomers.add(associateCustomer1);
        magazineModel.editPayingCustomerAssociatesList(payingCustomer, newAssociateCustomers);
        assertEquals(newAssociateCustomers, payingCustomer.getAssociateCustomerList());
    }

    @Test
    void testEditAssociateCustomerInfo() {
        magazineModel.editAssociateCustomerPayingCustomer(associateCustomer1, payingCustomer);
        assertEquals(payingCustomer, associateCustomer1.getPayingCustomer());
        assertTrue(payingCustomer.getAssociateCustomerList().contains(associateCustomer1));
    }

    @Test
    void testEditSupplementInfo() {
        magazineModel.addNewSupplement(supplement1);
        magazineModel.addNewSupplement(supplement2);

        //Test edit supplement name
        magazineModel.editSupplementInfo(supplement1, "New Supplement Name");
        assertEquals("New Supplement Name", supplement1.getName());

        // Test edit supplement cost
        magazineModel.editSupplementInfo(supplement2, 5.0);
        assertEquals(5.0, supplement2.getWeeklyCost());

        // Test edit supplement name and cost
        magazineModel.editSupplementInfo(supplement1, "Updated Supplement", 7.5);
        assertEquals("Updated Supplement", supplement1.getName());
        assertEquals(7.5, supplement1.getWeeklyCost());

    }


} // END OF MagazineModelTest CLASS
