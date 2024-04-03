package site.pangarm.mail.send.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import site.pangarm.mail.exception.ErrorCode;
import site.pangarm.mail.exception.MailException;
import site.pangarm.mail.send.dto.request.MailSendRequest;
import site.pangarm.mail.send.dto.response.MailSendResponse;


@Service
class MailServiceImpl implements MailSendService {

    private final JavaMailSender javaMailSender;
    private final String FROM_ADDRESS;

    public MailServiceImpl(JavaMailSender javaMailSender, @Value("${spring.mail.from}") String FROM_ADDRESS) {
        this.javaMailSender = javaMailSender;
        this.FROM_ADDRESS = FROM_ADDRESS;
    }

    @Override
    public MailSendResponse send(MailSendRequest request) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            message.setFrom(FROM_ADDRESS);
            message.setTo(request.getEmail());
            message.setSubject(request.getTitle());
            message.setText(request.getBody(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailException(ErrorCode.API_ERROR_INTERNAL_SERVER_ERROR);
        }
        return MailSendResponse.success();
    }
}
