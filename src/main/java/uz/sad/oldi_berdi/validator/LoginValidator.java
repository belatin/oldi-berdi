package uz.sad.oldi_berdi.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class LoginValidator {

//    private static final Pattern EMAIL_PATTERN =
//            Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+998\\s?\\d{9}$");

    public boolean isValid(String login) {
        if (login == null || login.isBlank())
            return false;
        String digitsOnly = login.replaceAll(" ", "");
        return PHONE_PATTERN.matcher(digitsOnly).matches();
    }
}
