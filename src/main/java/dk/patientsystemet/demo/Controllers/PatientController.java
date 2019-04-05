package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Note;
import dk.patientsystemet.demo.Model.Patient;
import dk.patientsystemet.demo.Model.User;
import dk.patientsystemet.demo.Service.ConsultationService;
import dk.patientsystemet.demo.Service.PatientService;
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
    public String findPatient(@ModelAttribute Patient patient, HttpSession session) throws SQLException {
        int id = service.searchPatient(patient);
        session.setAttribute("patient_id", id);
        return "redirect:/findPatient/" + id;
    }

    @GetMapping("/findPatient/{id}")
    public String getPatient(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("patient", service.findPatient(id));
        model.addAttribute("consultations", consultationService.getConsultations(id));
        model.addAttribute("diagnosis", service.getDiagnosis(id));
        model.addAttribute("notes", service.findPatientNote(id));
        model.addAttribute("title", "Patient Page");
        return "findPatient";
    }

    @PostMapping("/createNote/{id}")
    public String createNote(@PathVariable("id") int id, @ModelAttribute Patient patient, Model model) throws SQLException {
        model.addAttribute("message", service.createNote(patient, id));
        return "redirect:/findPatient/{id}";
    }

    @GetMapping("/deleteNote/{id}")
    public String deleteNote(@PathVariable("id") int id, @ModelAttribute Note note, HttpSession session) throws SQLException {
        service.deleteNote(id);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    /*@PostMapping("/addDiagnosis")
    public String addDiagnosis(@ModelAttribute Patient patient, @ModelAttribute User user, Model model) throws SQLException {
        model.addAttribute("diagnosis", service.newDiagnosis(patient.getId(), ));
        model.addAttribute("patient", service.findPatient(patient));
        return "";
    }*/

}
