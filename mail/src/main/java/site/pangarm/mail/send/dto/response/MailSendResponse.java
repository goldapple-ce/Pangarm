package site.pangarm.mail.send.dto.response;

import lombok.Getter;


@Getter
public class MailSendResponse {

    private String message;

    private MailSendResponse(String message) {
        this.message = message;
    }

    public static MailSendResponse success(){
        return new MailSendResponse("정상적으로 메일 발송에 성공하였습니다.");
    }
    public static MailSendResponse fail(){
        return new MailSendResponse("메일 발송에 실패했습니다.");
    }

}
