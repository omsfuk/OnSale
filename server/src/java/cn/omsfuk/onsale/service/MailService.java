package cn.omsfuk.onsale.service;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    public void sendActiveEmail(String email, String acCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = null;

            helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setFrom("knife037@126.com");
            helper.setTo(email);

            Template template = null;
            template = configuration.getTemplate("mail.html");
            Map<String, Object> model = new HashMap<>();
            model.put("email", email);
            email = java.net.URLEncoder.encode(email, "utf8");
            model.put("active_url", "http://localhost:8080/user/active?email=" + email + "&ac_code=" + acCode);

            Writer writer = new StringWriter();
            template.process(model, writer);
            String content = writer.toString();

            helper.setSubject("打折Ba账号激活");
            helper.setText(content, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
