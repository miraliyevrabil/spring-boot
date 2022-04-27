package com.rabilmiraliyev.kafkaexample;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private KafkaTemplate<String ,String > kafkaTemplate;

    public RestController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        MessageRequest request = new MessageRequest("notification from controller");
        kafkaTemplate.send("rabilmiraliyev", request.message());
        return ResponseEntity.ok("Succes");
    }
}
