package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Medicine;
import dk.patientsystemet.demo.Model.Prescription;
import dk.patientsystemet.demo.Repositories.ConsultationRepository;
import dk.patientsystemet.demo.Repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepository db;

    @Autowired
    Validate val;

    public List<Prescription> findPrescriptionByPatient(int id) {
        ResultSet rs = db.findPrescriptionByPatient(id);
        List<Prescription> prescriptionsList = new ArrayList<>();
        try {
            while (rs.next()) {
                Prescription pre = new Prescription();
                pre.setId(rs.getInt("prescription.id"));
                pre.setDescription(rs.getString("prescription.description"));
                pre.setDate(rs.getString("prescription.date"));
                prescriptionsList.add(pre);
            }
            return prescriptionsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Prescription> recentPrescriptions(int id) {
        ResultSet rs = db.recentPrescriptions(id);
        List<Prescription> prescriptionsList = new ArrayList<>();
        try {
            while (rs.next()) {
                Prescription pre = new Prescription();
                pre.setId(rs.getInt("prescription.id"));
                pre.setDescription(rs.getString("prescription.description"));
                pre.setDate(rs.getString("prescription.date"));
                pre.setPatientId(rs.getInt("prescription.fk_patient"));
                prescriptionsList.add(pre);
            }
            return prescriptionsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Prescription> findPrescriptionById(int id) {
        ResultSet rs = db.findPrescriptionById(id);
        List<Prescription> prescriptionsList = new ArrayList<>();
        try {
            while (rs.next()) {
                Prescription pre = new Prescription();
                pre.setId(rs.getInt("prescription.id"));
                pre.setDescription(rs.getString("prescription.description"));
                pre.setDate(rs.getString("prescription.date"));
                prescriptionsList.add(pre);
            }
            return prescriptionsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Medicine> findMedicineByPrescription(int id) {
        ResultSet rs = db.findMedicineByPrescription(id);
        List<Medicine> medicineList = new ArrayList<>();
        try {
            while (rs.next()) {
                Medicine med = new Medicine();
                med.setId(rs.getInt("medicine.id"));
                med.setName(rs.getString("medicine.name"));
                medicineList.add(med);
            }
            return medicineList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Medicine> getAllMedicine() {
        ResultSet rs = db.getAllMedicine();
        List<Medicine> medicineList = new ArrayList<>();
        try {
            while (rs.next()) {
                Medicine med = new Medicine();
                med.setId(rs.getInt("medicine.id"));
                med.setName(rs.getString("medicine.name"));
                medicineList.add(med);
            }
            return medicineList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createPrescription(Prescription prescription, int medId, int userID) throws SQLException {
        if(val.betweenString(prescription.getDescription(), 3, 999)) {
            return "Error: Description has to be atleast 3 characters";
        } else {
            db.createPrescription(prescription);
            ResultSet rs = db.findPrescriptionByLastUser(userID);
            int preId = 0;
            while (rs.next()) {
                preId = rs.getInt("id");
            }
            db.createMedicine(medId, preId);
            return "Success";
        }
    }
    public String addMedicine(int medId, int preId) {
        db.createMedicine(medId, preId);
        return "Success";
    }

    public String deleteMedicine(int medId, int preId) {
        db.deleteMedicine(medId, preId);
        return "Success";
    }
    public String deletePrescription(int preId) {
        db.deletePrescription(preId);
        return "Success";
    }
    public String editPrescription(Prescription prescription) {
        if (val.biggerOrEqualToNumber(prescription.getDescription(), 2)) {
            return "Error: Description has to be atleast 3 characters";
        } else {
            db.editPrescription(prescription);
            return "Success";
        }
    }
}
