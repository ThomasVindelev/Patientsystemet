package dk.patientsystemet.demo.Model;

public class Prescription {

    private int id;
    private int cprNumber;
    private String patientName;
    private String note;
    private String medicinName;
    private String date;
    private String description;
    private int patientId;
    private int doctorId;

    public Prescription() {
    }

    public Prescription(int id, int cprNumber, String patientName, String note, String medicinName, String date, String description, int patientId, int doctorId) {
        this.id = id;
        this.cprNumber = cprNumber;
        this.patientName = patientName;
        this.note = note;
        this.medicinName = medicinName;
        this.date = date;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(int cprNumber) {
        this.cprNumber = cprNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMedicinName() {
        return medicinName;
    }

    public void setMedicinName(String medicinName) {
        this.medicinName = medicinName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
