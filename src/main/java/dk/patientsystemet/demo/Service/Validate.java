package dk.patientsystemet.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class Validate {

    public boolean biggerOrEqualToNumber(String string, int number) {
        if (string.length() <= number) {
            return true;
        } else {
            return false;
        }
    }

    public boolean betweenInt(int value, int min, int max) {
        if (value< min && value > max) {
            return true;
        } else {
            return false;
        }
    }

}
