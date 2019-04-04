package dk.patientsystemet.demo.Controllers;

import dk.patientsystemet.demo.Model.User;
import dk.patientsystemet.demo.Service.LoginService;
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
    public String getMainPage(Model model) {
        model.addAttribute("title", "Main");
        return "main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

}
