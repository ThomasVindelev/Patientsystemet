package dk.patientsystemet.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class Validate {

    public boolean biggerOrEqualToNumber(String string, int min) {
        if (string.length() <= min) {
            return true;
        } else {
            return false;
        }
    }

    public boolean betweenString(String value, int min, int max) {
        if (value.length() <= min || value.length() >= max) {
            return true;
        } else {
            return false;
        }
    }

    public boolean betweenInt(int value, int min, int max) {
        if (value <= min || value >= max) {
            return true;
        } else {
            return false;
        }
    }

}
