package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Medicine;
import dk.patientsystemet.demo.Model.Prescription;
import dk.patientsystemet.demo.Service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping("/prescription/{id}")
    public String findPrescriptionById(@PathVariable("id") int id, Model model) {
        model.addAttribute("prescription", prescriptionService.findPrescriptionById(id));
        model.addAttribute("medicine", prescriptionService.findMedicineByPrescription(id));
        return "prescription";
    }
    @PostMapping("/createPrescription")
    public String createPrescription(@ModelAttribute Prescription prescription, Medicine medicine, HttpSession session) throws SQLException {
        prescriptionService.createPrescription(prescription, medicine.getMedicineId(), prescription.getDoctorId());
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

}
