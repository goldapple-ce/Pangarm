package site.pangarm.mail.send.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSendRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    public MailSendRequest(String email, String title, String body) {

        if(email == null || !isValidEmail(email)){
            //TOD : Exception change
            throw new RuntimeException("Email Valid Error");
        }
        this.email = email;
        this.title = title;
        this.body = body;
    }
    private boolean isValidEmail(String email){
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
