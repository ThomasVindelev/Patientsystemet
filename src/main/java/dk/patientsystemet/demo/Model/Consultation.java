package dk.patientsystemet.demo.Model;

public class Consultation {

    private int id;
    private int patientId;
    private int userId;
    private String patientName;
    private int cprNumber;
    private String description;
    private String conclusion;
    private String date;

    public Consultation() {
    }

    public Consultation(int id, String patientName, int cprNumber, String description, String conclusion, String date) {
        this.id = id;
        this.patientName = patientName;
        this.cprNumber = cprNumber;
        this.description = description;
        this.conclusion = conclusion;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getCpr() {
        return cprNumber;
    }

    public void setCpr(int cprNumber) {
        this.cprNumber = cprNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
