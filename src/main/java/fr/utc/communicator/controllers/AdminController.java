package fr.utc.communicator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String login() {
        return "admin";
    }
}


