package br.com.petrim.lich.socket.controller;

import br.com.petrim.lich.socket.message.ProcessSocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ProcessNotificationController {

    @MessageMapping("/process_executed")
    @SendTo("/ws_lich_topic/process_executed")
    public ProcessSocketMessage notify(String message) {
        return new ProcessSocketMessage(message);
    }

}
