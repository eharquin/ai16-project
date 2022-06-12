package fr.utc.communicator.controllers;

import fr.utc.communicator.messages.MessageSent;
import fr.utc.communicator.messages.WSMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {

    @MessageMapping("/channels/{channelID}/send")
    @SendTo("/topic/channels/{channelID}")
    public MessageSent sendMessage(@DestinationVariable String channelID, MessageSent message, Principal principal) throws Exception {
        message.setUsername(principal.getName());
        return message;

    }

    @SubscribeMapping("/topic/channels/{channelID}")
    public void handleSubscription(@DestinationVariable String channelID, Principal principal) {
        System.out.println("subscribed " + principal.getName() + " to " + channelID);
    }

}
