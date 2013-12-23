package co.da.nw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MediatorController {

    @RequestMapping
    public String getHomePage() {
        // TODO: Change this to "redirect:/home"
        return "redirect:/home";
    }
}