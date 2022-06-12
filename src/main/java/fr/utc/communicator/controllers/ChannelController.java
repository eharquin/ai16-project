package fr.utc.communicator.controllers;

import fr.utc.communicator.models.Channel;
import fr.utc.communicator.models.User;
import fr.utc.communicator.repositories.ChannelRepository;
import fr.utc.communicator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/channels")
    public ModelAndView index() throws Exception {
        List<Channel> channels = channelRepository.findAll();
        ModelAndView mav = new ModelAndView("channels/index");
        mav.addObject("channels", channels);
        return mav;
    }

    @GetMapping("/channels/create")
    public ModelAndView create() throws Exception {
        List<Channel> channels = channelRepository.findAll();
        ModelAndView mav = new ModelAndView("channels/create");
        mav.addObject("channels", channels);
        return mav;
    }

    @PostMapping("/channels")
    public String store(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("date") String date, @RequestParam("duration") String duration, Principal principal) throws Exception {
        Channel channel = new Channel();
        channel.setName(name);
        channel.setDescription(description);
        channel.setDate(date);
        channel.setDuration(duration);
        User owner = userRepository.findByName(principal.getName());
        channel.setOwner(owner);
        channelRepository.save(channel);

        return "redirect:/channels";
    }

    @GetMapping("/channels/{channelID}")
    public ModelAndView show(@PathVariable Long channelID) throws Exception {
        List<Channel> channels = channelRepository.findAll();
        Optional<Channel> currentChannel = channelRepository.findById(channelID);
        ModelAndView mav = new ModelAndView("channels/show");
        mav.addObject("channels", channels);
        mav.addObject("currentChannel", currentChannel.get());
        return mav;
    }

    @DeleteMapping("/channels/{channelID}")
    public String destroy(@PathVariable("channelId") Long channelID) throws Exception {
        Optional<Channel> channel = channelRepository.findById(channelID);
        channelRepository.delete(channel.get());
        return "redirect:/channels";
    }
}