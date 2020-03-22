package dk.patientsystemet.demo.Model;

public class Medicine {
    private int id;
    private String name;
    private int medicineId;
    private int prescriptionId;

    public Medicine() {

    }

    public Medicine(int id, String name, int medicineId, int prescriptionId) {
        this.id = id;
        this.name = name;
        this.medicineId = medicineId;
        this.prescriptionId = prescriptionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}

