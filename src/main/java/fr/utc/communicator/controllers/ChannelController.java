package fr.utc.communicator.controllers;

import fr.utc.communicator.models.Channel;
import fr.utc.communicator.models.User;
import fr.utc.communicator.repositories.ChannelRepository;
import fr.utc.communicator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public ModelAndView index(Principal principal) throws Exception {
        ModelAndView mav = new ModelAndView("channels/index");
        return buildModelAndView(mav, principal);
    }

    @GetMapping("/channels/create")
    public ModelAndView create(Principal principal) throws Exception {
        ModelAndView mav = new ModelAndView("channels/create");
        return buildModelAndView(mav, principal);
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
    public ModelAndView show(@PathVariable Long channelID, Principal principal) throws Exception {
        ModelAndView mav = new ModelAndView("channels/show");
        Optional<Channel> currentChannel = channelRepository.findById(channelID);
        mav.addObject("currentChannel", currentChannel.get());
        List<User> users = userRepository.findAll();
        mav.addObject("users", users);
        return buildModelAndView(mav, principal);
    }

    @PostMapping("/channels/{channelId}/delete")
    @Transactional
    public String destroy(@PathVariable("channelId") Long channelID) throws Exception {
        Optional<Channel> channel = channelRepository.findById(channelID);
        channelRepository.delete(channel.get());
        return "redirect:/channels";
    }

    @PostMapping("/channels/{channelId}/addMember")
    public String addMember(@PathVariable("channelId") Long channelID, @RequestParam("userId") Long userId) throws Exception {
        Optional<Channel> channel = channelRepository.findById(channelID);
        Optional<User> user = userRepository.findById(userId);
        channel.get().getMembers().add(user.get());
        channelRepository.save(channel.get());

        return "redirect:/channels/" + channelID;
    }

    protected ModelAndView buildModelAndView(ModelAndView modelAndView, Principal principal) {
        List<Channel> channels = channelRepository.findAll();
        modelAndView.addObject("channels", channels);

        User owner = userRepository.findByName(principal.getName());
        List<Channel> ownedChannels = channelRepository.findByOwner(owner);
        modelAndView.addObject("ownedChannels", ownedChannels);

        List<Channel> invitedChannels = owner.getChannels();
        invitedChannels.removeIf(c -> c.getOwner().getId() == owner.getId());
        modelAndView.addObject("invitedChannels", invitedChannels);

        return modelAndView;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        System.out.println(e.getMessage());
    }
}