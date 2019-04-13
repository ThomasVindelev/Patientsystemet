package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.User;
import dk.patientsystemet.demo.Repositories.PrescriptionRepository;
import dk.patientsystemet.demo.Service.ConsultationService;
import dk.patientsystemet.demo.Service.DiagnosisService;
import dk.patientsystemet.demo.Service.LoginService;
import dk.patientsystemet.demo.Service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class LoginController {

    @Autowired
    LoginService service;

    @Autowired
    ConsultationService consultationService;

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    DiagnosisService diagnosisService;

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) throws SQLException {
        if (service.verifyUser(user)) {
            session.setAttribute("email", user.getEmail());
            session.setAttribute("name", user.getName());
            session.setAttribute("id", user.getId());
            session.setAttribute("role", user.getRoleName());
            return "redirect:/main";
        } else {
            model.addAttribute("invalid", true);
            return "index";
        }
    }

    @GetMapping("/main")
    public String getMainPage(Model model, HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("id");
        model.addAttribute("title", "Main");
        model.addAttribute("consultations", consultationService.upcomingConsultations(userId, session));
        model.addAttribute("prescriptions", prescriptionService.recentPrescriptions(userId, session));
        model.addAttribute("diagnosis", diagnosisService.getNewDiagnosis());
        return "main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

}
