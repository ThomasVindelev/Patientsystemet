package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Diagnosis;
import dk.patientsystemet.demo.Service.DiagnosisService;
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
public class DiagnosisController {

    @Autowired
    DiagnosisService diagnosisService;

    @GetMapping("/getDiagnosis/{id}")
    public String viewDiagnosis(@PathVariable("id") int id, Model model) {
        Diagnosis diagnosis = diagnosisService.viewDiagnosis(id);
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("medicineList", diagnosisService.getMedicineByDiagnosis(diagnosis.getNameID()));
        return "viewDiagnosis";
    }

    @PostMapping("/getDiagnosis/{id}")
    public String editDiagnosis() {
        return "";
    }

    @PostMapping("/addDiagnosis")
    public String addDiagnosis(@ModelAttribute Diagnosis diagnosis, HttpSession session) {
        diagnosisService.newDiagnosis(diagnosis, diagnosis.getPatientId(), diagnosis.getDoctorId());
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @PostMapping("/addUnknownDiagnosis")
    public String addUnknownDiagnosis(@ModelAttribute Diagnosis diagnosis, String diagnosisName, HttpSession session) {
        diagnosisService.newUnknownDiagnosis(diagnosisName);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @GetMapping("/deleteDiagnosis/{id}")
    public String deleteDiagnosis(@PathVariable("id") int id, HttpSession session) {
        diagnosisService.deleteDiagnosis(id);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

}
