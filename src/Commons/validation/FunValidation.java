package Commons.validation;

public class FunValidation {
    private static String regex = "";

    public static boolean checkPhoneNumber(String str) {
        regex = "^(0[1-9][\\d]{8})|(\\+84[1-9][\\d]{8})$";
        return str.matches(regex);
    }


    public static boolean checkEmail(String str) {
        regex = "^[\\w\\d-_.]{3,20}@[\\w]{3,7}+(.[\\w]{2,5}){1,5}$";
        return str.matches(regex);
    }
}
