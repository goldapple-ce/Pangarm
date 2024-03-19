package site.pangarm.mail.send.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import site.pangarm.mail.exception.ErrorCode;
import site.pangarm.mail.exception.MailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
public class MailSendRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String body;


}
