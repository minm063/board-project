package com.example.board.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@Component
public class MailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    private int authNum;


    private void getAuthCode() {
        // authKey 생성
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);
        authNum = checkNum;
    }

    public String mailForm(String email) {
        getAuthCode();

        String setFrom = "bma0154@naver.com";
        String toMail = email;
        String emailTitle = "회원가입 인증 이메일";
        String emailContent = "인증번호 : "+authNum;
        sendMail(setFrom, toMail, emailTitle, emailContent);
        return Integer.toString(authNum);
    }
    private void sendMail(String setForm, String toMail, String emailTitle, String emailContent){
//        //provide recipient's email ID
//        String to = toMail;
//        //provide sender's email ID
//        String from = "bma0154@naver.com";
//        //provide Mailtrap's username
//        final String username = "1668c90a8642ad:d72e34e2224057";
//        //provide Mailtrap's password
//        final String password = "82a851fcf4aa33";
//        //provide Mailtrap's host address
//        String host = "smtp.//mailtrap.io:2525";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//
//                });
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setForm);
            helper.setTo(toMail);
            helper.setSubject(emailTitle);
            helper.setText(emailContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
