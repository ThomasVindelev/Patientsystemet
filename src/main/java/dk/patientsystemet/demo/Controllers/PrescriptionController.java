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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("allMedicine", prescriptionService.getAllMedicine());
        return "prescription";
    }
    @PostMapping("/createPrescription")
    public String createPrescription(@ModelAttribute Prescription prescription, Medicine medicine, HttpSession session, RedirectAttributes redirAttr) throws SQLException {
        redirAttr.addFlashAttribute("error", prescriptionService.createPrescription(prescription, medicine.getMedicineId(), prescription.getDoctorId()));

        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @PostMapping("/addMedicine/{id}")
    public String addMedicine(@PathVariable("id") int id, Medicine medicine, RedirectAttributes redirAttr) {
        redirAttr.addFlashAttribute("error", prescriptionService.addMedicine(medicine.getMedicineId(), id));
        return "redirect:/prescription/" + id;
    }
    @GetMapping("/deleteMedicine/{medId}/{preId}")
    public String deleteMedicine(@PathVariable("medId") int medId, @PathVariable("preId") int preId, Medicine medicine, RedirectAttributes redirAttr) {
        redirAttr.addFlashAttribute("error", prescriptionService.deleteMedicine(medId, preId));
        return "redirect:/prescription/" + preId;
    }

    @GetMapping("/deletePrescription/{id}")
    public String deletePrescription(@PathVariable("id") int preId, RedirectAttributes redirAttr, HttpSession session) {
        redirAttr.addFlashAttribute("error", prescriptionService.deletePrescription(preId));
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }
}
