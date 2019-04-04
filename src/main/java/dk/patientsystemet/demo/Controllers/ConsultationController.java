package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.Consultation;
import dk.patientsystemet.demo.Service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

@Controller
public class ConsultationController {

    @Autowired
    ConsultationService service;

    @GetMapping("/deleteConsultation/{id}")
    public String deleteConsultation(@PathVariable("id") int id, @ModelAttribute Consultation consultation) throws SQLException {
        service.deleteConsultation(id);
        return "redirect:/findPatient/" + consultation.getPatientId();
    }
}
