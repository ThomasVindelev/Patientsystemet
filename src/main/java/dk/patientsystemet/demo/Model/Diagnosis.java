package dk.patientsystemet.demo.Model;

import java.util.Date;

public class Diagnosis {

    private int id;
    private String diagnosisName;
    private String patientName;
    private String doctorName;
    private String note;
    private String date;

    public Diagnosis(int id, String diagnosisName, String patientName, String doctorName, String note, String date) {
        this.id = id;
        this.diagnosisName = diagnosisName;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.note = note;
        this.date = date;
    }

    public Diagnosis() {

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
