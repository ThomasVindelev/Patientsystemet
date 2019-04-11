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
    private String time;

    public Consultation() {
    }

    public Consultation(int id, int patientId, int userId, String patientName, int cprNumber, String description, String conclusion, String date, String time) {
        this.id = id;
        this.patientId = patientId;
        this.userId = userId;
        this.patientName = patientName;
        this.cprNumber = cprNumber;
        this.description = description;
        this.conclusion = conclusion;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(int cprNumber) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
