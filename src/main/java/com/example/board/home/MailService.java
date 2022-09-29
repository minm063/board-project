package com.example.board.home;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MailService {
    private int size;

    private String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }

    private String getAuthCode() {
        // authKey 생성
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        while (stringBuffer.length() < size) {
            stringBuffer.append(random.nextInt());
        }
        return stringBuffer.toString();
    }

    public String sendMail(String email) {
        String authKey = getKey(6);
        return authKey;
    }
}
