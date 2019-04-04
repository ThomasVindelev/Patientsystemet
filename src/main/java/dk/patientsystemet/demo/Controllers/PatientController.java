package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Patient;
import dk.patientsystemet.demo.Service.ConsultationService;
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

    @Autowired
    ConsultationService consultationService;

    @GetMapping("/createPatient")
    public String createPatient(Model model) {
        model.addAttribute("title", "Create patient");
        return "createPatient";
    }

    @GetMapping("/allPatients")
    public String allPatients(Model model) throws SQLException {
        model.addAttribute("title", "All patients");
        model.addAttribute("patients", service.fetchAll());
        return "patientList";
    }

    @PostMapping("/createPatient")
    public String createPatientForm(@ModelAttribute Patient patient, Model model) throws SQLException {
        model.addAttribute("title", "Create patient");
        model.addAttribute("error", service.createPatient(patient));
        return "createPatient";
    }

    @PostMapping("/findPatient")
    public String findPatient(@ModelAttribute Patient patient, Model model) throws SQLException {
        model.addAttribute("patient", service.findPatient(patient));
        model.addAttribute("consultations", consultationService.getConsultations(patient));
        model.addAttribute("notes", service.findPatientNote(patient.getId()));
        model.addAttribute("title", patient.getFirstName());
        return "findPatient";
    }

    @PostMapping("/createNote")
    public String createNote(@ModelAttribute Patient patient, Model model) throws SQLException {
        model.addAttribute("message", service.createNote(patient));
        model.addAttribute("patient", service.findPatient(patient));
        model.addAttribute("notes", service.findPatientNote(patient.getId()));
        model.addAttribute("title", patient.getFirstName());
        return findPatient(patient, model);
    }
}
