package helper;

public class Validation {

    public static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    public static boolean isValidCost(String strCost) {
        try {
            Double.parseDouble(strCost);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$");
    }

    public static boolean isValidPaymentMethod(String paymentMethod) {
        return paymentMethod.equalsIgnoreCase("Credit Card") ||
                paymentMethod.equalsIgnoreCase("Bank Acc Debit");
    }
    public static boolean isValidPaymentDetails(String paymentDetails) {
        return paymentDetails.matches("^[0-9]*$");
    }
}
