package fr.utc.communicator.controllers;

import fr.utc.communicator.models.User;
import fr.utc.communicator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("mail") String mail, @RequestParam("password") String password) {
        User user = userRepository.findByMail(mail);
        if (user == null) {
            user = new User();
            user.setName(username);
            user.setName(mail);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
        } else {
            System.out.println(mail + " already exist.");
        }

        return "redirect:/login";
    }
}
