package fr.utc.communicator.controllers;

import fr.utc.communicator.models.Channel;
import fr.utc.communicator.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping("/channels/{channelID}")
    public ModelAndView sendMessage(@PathVariable Long channelID) throws Exception {
        List<Channel> channels = channelRepository.findAll();
        Optional<Channel> currentChannel = channelRepository.findById(channelID);
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("channels", channels);
        mav.addObject("currentChannel", currentChannel.get());
        return mav;
    }
}