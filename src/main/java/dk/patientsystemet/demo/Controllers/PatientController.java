package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Patient;
import dk.patientsystemet.demo.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class PatientController {

    @Autowired
    PatientService service;

    @GetMapping("/createPatient")
    public String createPatient() {
        return "createPatient";
    }

    @PostMapping("/createPatient")
    public String createPatientForm(@ModelAttribute Patient patient, Model model) throws SQLException {
        model.addAttribute("error", service.createPatient(patient));
        return "createPatient";
    }

    @GetMapping("/findpatient")
    public String findPatient(@ModelAttribute Patient patient) {
        patient.getCpr();
        return "findPatient";
    }
}
