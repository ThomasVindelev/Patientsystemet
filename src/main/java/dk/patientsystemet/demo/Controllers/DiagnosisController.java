package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Diagnosis;
import dk.patientsystemet.demo.Service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/addDiagnosis")
    public String addDiagnosis(@ModelAttribute Diagnosis diagnosis, HttpSession session) throws SQLException {
        diagnosisService.newDiagnosis(diagnosis, diagnosis.getPatientId(), diagnosis.getDoctorId());
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @PostMapping("/addUnknownDiagnosis")
    public String addUnknownDiagnosis(@ModelAttribute Diagnosis diagnosis, String diagnosisName, HttpSession session) throws SQLException {
        diagnosisService.newUnknownDiagnosis(diagnosisName);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @GetMapping("/deleteDiagnosis/{id}")
    public String deleteDiagnosis(@PathVariable("id") int id, HttpSession session) throws SQLException {
        diagnosisService.deleteDiagnosis(id);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

}
