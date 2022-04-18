package com.rabilmiraliyev.websocketexample.controller;

import com.rabilmiraliyev.websocketexample.model.WsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
//    mesaji hamiya gonderir
//    @SendTo("/topic")
//    @SendToUser()
    public void chatEndpoint(@Payload WsMessage wsMessage){
        System.out.println(wsMessage);
        simpMessagingTemplate.convertAndSend("/topic",wsMessage);
    }
}
