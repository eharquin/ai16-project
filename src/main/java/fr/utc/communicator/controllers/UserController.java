package fr.utc.communicator.controllers;

import fr.utc.communicator.models.Channel;
import fr.utc.communicator.models.User;
import fr.utc.communicator.repositories.ChannelRepository;
import fr.utc.communicator.repositories.MessageRepository;
import fr.utc.communicator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @GetMapping("/test")
    public String test() {
//        User newUser = new User();
//        newUser.setName("Enzo");
//        newUser.setMail("..");
//        newUser.SetIsAdmin(true);
//        newUser.SetPassword("password");
//        userRepository.save(newUser);

//        List<User> users = userRepository.findAll();
//        users.forEach(user ->{
//            System.out.println(user.getName() + " " + user.getMail());
//        });
        long id = 1;
        Optional<Channel> channel = channelRepository.findById(id);
        System.out.println(channel.get().GetName() + channel.get().GetMessages());


        return "ok";
    }
}
