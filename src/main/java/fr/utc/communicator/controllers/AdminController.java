package fr.utc.communicator.controllers;

import fr.utc.communicator.models.User;
import fr.utc.communicator.messages.MessageSent;
import fr.utc.communicator.models.Channel;
import fr.utc.communicator.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import fr.utc.communicator.repositories.UserRepository;

import java.security.Principal;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String login() {
        return "admin";
    }

    @SendTo("/admin")
    public List<User> sendUserList() throws Exception {
        List<User> users = userRepository.findAll();
        return users;
    }

}




