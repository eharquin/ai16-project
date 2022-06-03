package fr.utc.communicator.controllers;

import fr.utc.communicator.messages.MessageSent;
import fr.utc.communicator.messages.WSMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {

    @MessageMapping("/channels/{channelID}/send")
    @SendTo("/topic/channels/{channelID}")
    public MessageSent sendMessage(@DestinationVariable String channelID, MessageSent message, Principal principal) throws Exception {
        //Thread.sleep(1000); // simulated delay
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");

        message.setUsername(principal.getName());

        return message;

    }

}
