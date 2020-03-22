package dk.patientsystemet.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Login");
        return "index";
    }

}

