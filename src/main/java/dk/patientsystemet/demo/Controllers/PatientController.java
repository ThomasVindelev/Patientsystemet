package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Patient;
import dk.patientsystemet.demo.Service.ConsultationService;
import dk.patientsystemet.demo.Service.DiagnosisService;
import dk.patientsystemet.demo.Service.PatientService;
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

@Controller
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    ConsultationService consultationService;

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    DiagnosisService diagnosisService;

    @GetMapping("/createPatient")
    public String createPatient(Model model) {
        model.addAttribute("title", "Create patient");
        return "createPatient";
    }

    @GetMapping("/allPatients")
    public String allPatients(Model model) {
        model.addAttribute("title", "All patients");
        model.addAttribute("patients", patientService.fetchAll());
        return "patientList";
    }
    @PostMapping("/allPatientSearch")
    public String allPatientSearchPost(@ModelAttribute Patient patient) {
        String word = patient.getSearchword();
        return "redirect:/patientListSearch/" + word;
    }

    @GetMapping("/patientListSearch/{word}")
    public String allPatientSearch(@PathVariable("word") String word, @ModelAttribute Patient patient, Model model) {
        model.addAttribute("patients", patientService.searchPatientList(word));
        return "patientListSearch";
    }

    @PostMapping("/createPatient")
    public String createPatientForm(@ModelAttribute Patient patient, Model model) {
        model.addAttribute("title", "Create patient");
        model.addAttribute("error", patientService.createPatient(patient));
        return "createPatient";
    }

    @PostMapping("/editPatient")
    public String editPatient(@ModelAttribute Patient patient, RedirectAttributes redirAttr) {
        redirAttr.addFlashAttribute("error", patientService.editPatient(patient));
        return "redirect:/findPatient/" + patient.getId();
    }

    @PostMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable("id") int id, RedirectAttributes redirAttr) {
        redirAttr.addFlashAttribute("error", patientService.deletePatient(id));
        return "redirect:/allPatients";
    }

    @PostMapping("/findPatient")
    public String findPatient(@ModelAttribute Patient patient) {
        int patient_id = patientService.searchPatient(patient);
        return "redirect:/findPatient/" + patient_id;
    }

    @GetMapping("/findPatient/{id}")
    public String getPatient(@PathVariable("id") int id, Model model, HttpSession session) {
        model.addAttribute("patient", patientService.findPatient(id));
        model.addAttribute("consultations", consultationService.getConsultations(id));
        model.addAttribute("diagnosis", diagnosisService.getDiagnosisByPatient(id, 1));
        model.addAttribute("diagnosisList", diagnosisService.getDiagnosis());
        model.addAttribute("notes", patientService.findPatientNote(id));
        model.addAttribute("prescription", prescriptionService.findPrescriptionByPatient(id));
        model.addAttribute("allMedicine", prescriptionService.getAllMedicine());
        session.setAttribute("patient_id", id);
        model.addAttribute("title", "Patient Page");
        return "findPatient";
    }

    @PostMapping("/createNote/{id}")
    public String createNote(@PathVariable("id") int id, @ModelAttribute Patient patient, RedirectAttributes redirAttr) {
        redirAttr.addFlashAttribute("error", patientService.createNote(patient, id));
        return "redirect:/findPatient/{id}";
    }

}

