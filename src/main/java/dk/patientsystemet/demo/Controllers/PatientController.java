package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PatientController {

    @GetMapping("/patient")
    public String createPatient() {
        return "createPatient";
    }

    @GetMapping("/findpatient")
    public String findPatient(@ModelAttribute Patient patient) {
        patient.getCprNumber();
        return "findPatient";
    }
}
