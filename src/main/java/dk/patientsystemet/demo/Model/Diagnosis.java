package dk.patientsystemet.demo.Model;

import java.util.Date;

public class Diagnosis {

    private int id;
    private String diagnosisName;
    private String patientName;
    private String doctorName;
    private String medicineName;
    private int patientId;
    private int doctorId;
    private int nameID;
    private String note;
    private String date;

    public Diagnosis() {
    }

    public Diagnosis(int id, String diagnosisName, String patientName, String doctorName, String medicineName, int patientId, int doctorId, int nameID, String note, String date) {
        this.id = id;
        this.diagnosisName = diagnosisName;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.medicineName = medicineName;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.nameID = nameID;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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

    public int getNameID() {
        return nameID;
    }

    public void setNameID(int nameID) {
        this.nameID = nameID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

