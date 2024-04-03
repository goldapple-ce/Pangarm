package site.pangarm.mail.send.service;

import site.pangarm.mail.send.dto.request.MailSendRequest;
import site.pangarm.mail.send.dto.response.MailSendResponse;


public interface MailSendService {

    MailSendResponse send(MailSendRequest request);
}
