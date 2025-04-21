package helper;

import customer.AssociateCustomer;
import customer.Customer;
import customer.PayingCustomer;
import magazine.Magazine;
import magazine.Supplement;
import model.MagazineModel;

import java.util.ArrayList;


public class TestDataLoader {

    public static MagazineModel loadTestMagazineModel() {
        MagazineModel magazineModel = new MagazineModel();
        magazineModel.setMagazine(loadTestMagazine());
        magazineModel.setSupplements(loadTestSupplements());
        magazineModel.setCustomers(loadTestCustomers(loadTestSupplements()));
        magazineModel.setFileName("TestMagazineModel.ser");

        return magazineModel;
    }

    public static Magazine loadTestMagazine() {
        Magazine magazine = new Magazine("Grand Line Gazette", 20.0);
        return magazine;
    }

    public static ArrayList<Supplement> loadTestSupplements() {
        ArrayList<Supplement> supplements = new ArrayList<>();
        supplements.add(new Supplement("Guide to Devil Fruits", 7.0));
        supplements.add(new Supplement("Haki Mastery", 6.5));
        supplements.add(new Supplement("Navigator's Almanac", 6.0));
        supplements.add(new Supplement("Sea Monster Spotter", 5.5));
        supplements.add(new Supplement("Shipwright's Showcase", 5.5));
        supplements.add(new Supplement("Marine Muster", 4.0));
        supplements.add(new Supplement("Island Survival Tips", 4.5));
        supplements.add(new Supplement("Dream Chasers: Worst Generation", 6.0));
        supplements.add(new Supplement("Myths and Legends of the Grand Line", 3.5));
        supplements.add(new Supplement("Pirate Crews and Alliances", 4.0));
        supplements.add(new Supplement("Wanted Posters Collection", 5.0));
        supplements.add(new Supplement("Treasure A-Hunting", 6.0));

        return supplements;
    }

    public static ArrayList<Customer> loadTestCustomers(ArrayList<Supplement> supplementsList) {
        ArrayList<Customer> customers = new ArrayList<>();
        //Paying customer with no supplements and no associate customers
        customers.add(new PayingCustomer("Paying", "Monkey D. Luffy",
                "1, Foosha Village, East Blue, 548525",
                "rubberking@op.com",
                "Credit Card", "4111555544447859"));
        customers.add(new PayingCustomer("Paying", "Portgas D. Ace",
                "154, Baterilla, South Blue, 155331",
                "flamefist@op.com",
                "Credit Card", "9983647333332222"));

        //Paying customer with supplements and no associate customer
        ArrayList<Supplement> zoroSupplements = new ArrayList<>();
        zoroSupplements.add(supplementsList.get(1));
        zoroSupplements.add(supplementsList.get(5));
        customers.add(new PayingCustomer("Paying", "Roronoa Zoro",
                "3, Shimotsuki Village, East Blue, 788854",
                "swordsman123@op.com", zoroSupplements,
                "Bank Acc Debit", "523135567"));
        ArrayList<Supplement> saboSupplements = new ArrayList<>();
        saboSupplements.add(supplementsList.get(1));
        saboSupplements.add(supplementsList.get(8));
        saboSupplements.add(supplementsList.get(2));
        customers.add(new PayingCustomer("Paying", "Sabo",
                "583, Goa Kingdom, East Blue, 877844",
                "flameemperor@op.com", saboSupplements,
                "Bank Acc Debit", "772617890"));

        //Paying customers with no supplements and with associate customer
        customers.add(new PayingCustomer("Paying", "Vinsmoke Sanji",
                        "43, Germa Kingdom, North Blue, 455552",
                "loverboychef@op.com",
                "Credit Card", "45685247598998545"));

        customers.add(new PayingCustomer("Paying", "Nico Robin",
                        "114, Ohara, West Blue, 985899",
                "archaelogist424@op.com",
                "Credit Card", "4522214475639985"));
        customers.add(new PayingCustomer("Paying", "Bartolomeo Barto",
                "705, Loguetown, Ease Blue, 442355",
                "bartoclub@op.com",
                "Credit Card", "4456782766789991"));

        //Paying customer with supplements and with associate customer
        ArrayList<Supplement> frankySupplements = new ArrayList<>();
        frankySupplements.add(supplementsList.get(0));
        frankySupplements.add(supplementsList.get(5));
        frankySupplements.add(supplementsList.get(4));
        frankySupplements.add(supplementsList.get(10));
        customers.add(new PayingCustomer("Paying", "Franky Flam",
                        "329, Water 7, South Blue, 322563",
                "supercola38@op.com", frankySupplements,
                "Bank Acc Debit", "558744598"));
        ArrayList<Supplement> fujitoraSupplements = new ArrayList<>();
        fujitoraSupplements.add(supplementsList.get(11));
        fujitoraSupplements.add(supplementsList.get(8));
        fujitoraSupplements.add(supplementsList.get(9));
        customers.add(new PayingCustomer("Paying", "Issho Fujitora",
                "701, Aoi Kingdom, Grand Line, 443256",
                "humanejustice@op.com", fujitoraSupplements,
                "Bank Acc Debit", "235551789"));
        ArrayList<Supplement> peronaSupplements = new ArrayList<>();
        peronaSupplements.add(supplementsList.get(0));
        peronaSupplements.add(supplementsList.get(3));
        peronaSupplements.add(supplementsList.get(4));
        customers.add(new PayingCustomer("Paying", "Perona",
                "443, Kuraigana Island, West Blue, 443245",
                "ghostprincess@op.com", peronaSupplements,
                "Bank Acc Debit", "009846778"));

        //Associate customer with no supplements
        customers.add(new AssociateCustomer("Associate", "Tony Tony Chopper",
                        "134, Drum Island, Grand Line, 125521",
                "doctorreindeer99@op.com",
                (PayingCustomer) customers.get(3)));
        customers.add(new AssociateCustomer("Associate", "Jimbei",
                "546, Fish-Man Island, Grand Line, 235234",
                "fishmanjujutsu@op.com",
                (PayingCustomer) customers.get(5)));

        //Associate customer with supplements
        ArrayList<Supplement> yamatoSupplements = new ArrayList<>();
        yamatoSupplements.add(supplementsList.get(9));
        yamatoSupplements.add(supplementsList.get(2));
        yamatoSupplements.add(supplementsList.get(6));
        customers.add(new AssociateCustomer("Associate", "Yamato Kaidou",
                        "971, Onigashima, Wano Country, 654555",
                "onihime@op.com", yamatoSupplements, (PayingCustomer) customers.get(2)));

        ArrayList<Supplement> hancockSupplements = new ArrayList<>();
        hancockSupplements.add(supplementsList.get(2));
        hancockSupplements.add(supplementsList.get(8));
        customers.add(new AssociateCustomer("Associate", "Boa Hancock",
                        "516, Amazon Lily, Calm Belt, 844521",
                "snakeprincess@op.com", hancockSupplements, (PayingCustomer) customers.get(4)));
        ArrayList<Supplement> mihawkSupplements = new ArrayList<>();
        mihawkSupplements.add(supplementsList.get(2));
        mihawkSupplements.add(supplementsList.get(5));
        customers.add(new AssociateCustomer("Associate", "Dracule Mihawk",
                "54, Kuraigana Island, Grand Line, 566578",
                "strongestswordsman@op.com", mihawkSupplements, (PayingCustomer) customers.get(6)));
        ArrayList<Supplement> bonSupplements = new ArrayList<>();
        bonSupplements.add(supplementsList.get(0));
        bonSupplements.add(supplementsList.get(1));
        bonSupplements.add(supplementsList.get(7));
        bonSupplements.add(supplementsList.get(10));
        customers.add(new AssociateCustomer("Associate", "Bon Clay",
                "129, Newkama, East Blue, 988742",
                "mister2@op.com", bonSupplements, (PayingCustomer) customers.get(3)));

        //Add associate customers to paying customers
        ((PayingCustomer) customers.get(2)).addAssociateCustomer((AssociateCustomer) customers.get(12));
        ((PayingCustomer) customers.get(3)).addAssociateCustomer((AssociateCustomer) customers.get(15));
        ((PayingCustomer) customers.get(3)).addAssociateCustomer((AssociateCustomer) customers.get(10));
        ((PayingCustomer) customers.get(4)).addAssociateCustomer((AssociateCustomer) customers.get(13));
        ((PayingCustomer) customers.get(5)).addAssociateCustomer((AssociateCustomer) customers.get(11));
        ((PayingCustomer) customers.get(6)).addAssociateCustomer((AssociateCustomer) customers.get(14));

        return customers;
    }

}
