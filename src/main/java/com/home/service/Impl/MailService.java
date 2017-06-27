package com.home.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Nazar on 27.06.2017.
 */
@Service
public class MailService {


    private JavaMailSender mailSender;
    private XlsUpload xlsUpload;

    @Autowired
    public MailService(JavaMailSender mailSender, XlsUpload xlsUpload) {
        this.mailSender = mailSender;
        this.xlsUpload = xlsUpload;
    }

    public void sendMail(final String emailTo) {
        mailSender.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(
                        mimeMessage, true, "UTF-8");
                messageHelper.setTo(emailTo);

                messageHelper.addAttachment("report", new InputStreamSource() {

                    @Override
                    public InputStream getInputStream() throws IOException {
                        return new ByteArrayInputStream(xlsUpload.writeReportToXLS());
                    }
                });
            }
        });

    }
}
