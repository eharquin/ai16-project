package fr.utc.communicator.controllers;

import fr.utc.communicator.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.utc.communicator.repositories.UserRepository;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @SendTo("/admin")
    public List<User> sendUserList() throws Exception {
        List<User> users = userRepository.findAll();
        return users;
    }

    @PostMapping("/admin")
    public ModelAndView showUsers()
    {
        List<User> users = userRepository.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userLists");
        mv.addObject("tab",users);
        return mv;
    }

    @PostMapping("/admin/add")
    public void addUser(@RequestParam("username") String username, @RequestParam("mail") String mail, @RequestParam("password") String password ) {
        User user = userRepository.findByMail(mail);
        if (user == null) {
            user = new User();
            user.setName(username);
            user.setMail(mail);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
        }
        else
        {
            System.out.println(mail + " already exist.");
        }
    }

    @DeleteMapping("/admin/delete")
    public void removeUser(@RequestParam("mail") String mail) {
        User user = userRepository.findByMail(mail);
        if (user != null & true)
        {
            userRepository.delete(user);
        }
        else
        {
            System.out.println(mail + "can t delete admin or unknow user.");
        }
    }

    /*@PostMapping("/admin")
    public void modifyUser(@RequestParam("mail") String mail) {
        User oldUser = userRepository.findByMail(mail);
        if (oldUser != null)
        {
            User user = new User();
            user.SetName(username);
            user.SetMail(mail);
            user.SetPassword(bCryptPasswordEncoder.encode(password));

            userRepository.flush(user);
        }
        else
        {
            System.out.println(mail + "can t delete admin or unknow user.");
        }
    }*/
}




