package dk.patientsystemet.demo.Service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Validate {

    Random random;
    int randomNumber;

    public boolean biggerOrEqualToNumber(String string, int min) {
        if (string.length() <= min) {
            return true;
        } else {
            return false;
        }
    }

    public boolean betweenString(String value, int min, int max) {
        if (value.length() < min || value.length() >= max) {
            return true;
        } else {
            return false;
        }
    }

    public boolean betweenInt(int value, int min, int max) {
        if (value < min || value >= max) {
            return true;
        } else {
            return false;
        }
    }

    public boolean tjekRecept() {
        randomNumber = random.nextInt(20);
        if (randomNumber == 1) {
            return false;
        } else {
            return true;
        }
    }

}
