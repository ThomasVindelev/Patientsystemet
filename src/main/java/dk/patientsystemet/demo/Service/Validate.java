package dk.patientsystemet.demo.Service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Validate {

    private Random random = new Random();
    private int randomNumber;
    private String cprString;

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

    public boolean validateCPR(String cpr) {
        if (cpr.length() == 4) {
            return false;
        } else {
            return true;
        }
    }

    public boolean zipNumber(int cpr) {
        cprString = Integer.toString(cpr);
        if (cprString.length() == 4) {
            return false;
        } else {
            return true;
        }
    }

    public boolean phoneNumber(int cpr) {
        cprString = Integer.toString(cpr);
        if (cprString.length() == 8) {
            return false;
        } else {
            return true;
        }
    }

    public boolean tjekRecept() {
        randomNumber = random.nextInt(20);
        if (randomNumber == 1) {
            return true;
        } else {
            return false;
        }
    }

}
