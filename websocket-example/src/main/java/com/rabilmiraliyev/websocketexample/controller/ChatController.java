package com.rabilmiraliyev.websocketexample.controller;

import com.rabilmiraliyev.websocketexample.model.WsMessage;
import com.rabilmiraliyev.websocketexample.repository.WsMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private WsMessageRepository wsMessageRepository;
    @MessageMapping("/chat")
//    mesaji hamiya gonderir
//    @SendTo("/topic")
//    @SendToUser()
    public void chatEndpoint(@Payload WsMessage wsMessage){
        System.out.println(wsMessage);
        wsMessageRepository.save(wsMessage);
        simpMessagingTemplate.convertAndSend("/topic",wsMessage);
    }
   @Async
    @RequestMapping(value = "/notifications",method = RequestMethod.GET)
    public String get(){
        return "Notifications on Console";
    }

}
