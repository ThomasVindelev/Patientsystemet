package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Diagnosis;
import dk.patientsystemet.demo.Model.Note;
import dk.patientsystemet.demo.Model.Patient;
import dk.patientsystemet.demo.Model.User;
import dk.patientsystemet.demo.Service.ConsultationService;
import dk.patientsystemet.demo.Service.PatientService;
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
public class PatientController {

    @Autowired
    PatientService service;

    @Autowired
    ConsultationService consultationService;

    @Autowired
    PrescriptionService prescriptionService;

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
        int patient_id = service.searchPatient(patient);
        return "redirect:/findPatient/" + patient_id;
    }

    @GetMapping("/findPatient/{id}")
    public String getPatient(@PathVariable("id") int id, Model model, HttpSession session) throws SQLException {
        model.addAttribute("patient", service.findPatient(id));
        model.addAttribute("consultations", consultationService.getConsultations(id));
        model.addAttribute("diagnosis", service.getDiagnosisByPatient(id));
        model.addAttribute("diagnosisList", service.getDiagnosis());
        model.addAttribute("notes", service.findPatientNote(id));
        model.addAttribute("prescription", prescriptionService.findPrescriptionByPatient(id));
        model.addAttribute("medicine", prescriptionService.getAllMedicine());
        session.setAttribute("patient_id", id);
        model.addAttribute("title", "Patient Page");
        return "findPatient";
    }

    @PostMapping("/createNote/{id}")
    public String createNote(@PathVariable("id") int id, @ModelAttribute Patient patient, Model model) throws SQLException {
        model.addAttribute("message", service.createNote(patient, id));
        return "redirect:/findPatient/{id}";
    }

    @PostMapping("/addDiagnosis")
    public String addDiagnosis(@ModelAttribute Diagnosis diagnosis, HttpSession session) throws SQLException {
        service.newDiagnosis(diagnosis, diagnosis.getPatientId(), diagnosis.getDoctorId());
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @PostMapping("/addUnknownDiagnosis")
    public String addUnknownDiagnosis(@ModelAttribute Diagnosis diagnosis, String diagnosisName, HttpSession session) throws SQLException {
        service.newUnknownDiagnosis(diagnosisName);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

}
