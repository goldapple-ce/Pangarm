package site.pangarm.mail.send.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.pangarm.mail.send.dto.request.MailSendRequest;

@RestController
@RequestMapping("/mail")
public class MailSendController {


    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody @Valid MailSendRequest request){

        return ResponseEntity.ok("hello world");
    }

}
