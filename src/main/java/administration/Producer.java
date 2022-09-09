package administration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Producer {
    private String name;
    private Address address;
    private String phoneNumber;
    private String email;

    public Producer(String name, Address address, String phoneNumber, String email){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        boolean isValidEmail = validateEmail(email);
        if (isValidEmail){
            this.email = email;
        }
    }

    @Override
    public String toString() {
        return name +
                " (" + address +
                "; Telefonnummer " + phoneNumber +
                "; Email " + email + ")";
    }

    public String getName() {
        return name;
    }

    private boolean validateEmail(String email){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
