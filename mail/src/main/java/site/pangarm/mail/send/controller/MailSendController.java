package site.pangarm.mail.send.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.pangarm.mail.send.service.MailSendService;
import site.pangarm.mail.send.dto.request.MailSendRequest;
import site.pangarm.mail.send.dto.response.MailSendResponse;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailSendController {

    private final MailSendService mailSendService;

    @PostMapping("/send")
    public ResponseEntity<MailSendResponse> sendMail(@RequestBody @Valid MailSendRequest request){
        MailSendResponse response = mailSendService.send(request);

        return ResponseEntity.ok( response);
    }

}
