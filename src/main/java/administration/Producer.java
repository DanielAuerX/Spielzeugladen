package administration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Producer {
    String name;
    Address address;
    int phoneNumber;
    String email;

    public Producer(String name, Address address, int phoneNumber, String email){
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
        return "Producer{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }

    private boolean validateEmail(String email){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
