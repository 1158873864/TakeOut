package takeout.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import takeout.blservice.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String sendTo, String titel, String content) {
        System.out.println(content);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1919739928@qq.com");
        message.setTo(sendTo);
        message.setSubject( titel);
        message.setText(content);
        mailSender.send(message);
    }
}