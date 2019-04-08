package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Consultation;
import dk.patientsystemet.demo.Service.ConsultationService;
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
public class ConsultationController {

    @Autowired
    ConsultationService service;

    @GetMapping("/deleteConsultation/{id}")
    public String deleteConsultation(@PathVariable("id") int id, HttpSession session) throws SQLException {
        service.deleteConsultation(id);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @PostMapping("/createConsultation")
    public String createConsultation(@ModelAttribute Consultation consultation, HttpSession session) throws SQLException {
        service.createConsultation(consultation);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @GetMapping("/watchConsultation/{id}")
    public String watchConsultation(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("consultation", service.findConsultationById(id));
        return "watchConsultation";
    }

    @GetMapping("/editConsultation/{id}")
    public String GetEditConsultation(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("consultation", service.findConsultationById(id));
        return "editConsultation";
    }

    @PostMapping("/editConsultation/{id}")
    public String editConsultation(@ModelAttribute Consultation consultation, HttpSession session) throws SQLException {
        service.editConsultation(consultation);
        return "redirect:/findPatient/" + session.getAttribute("patient_id");
    }

    @GetMapping("/consultations/{id}")
    public String consultations(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("consultations", service.findDoctorsConsultationsById(id));
        return "consultations";
    }



}
