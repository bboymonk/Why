package com.wjb.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Administrator on 2017/11/27.
 */
public class SendEmail {
    public SendEmail() {
    }

    /**
     * 客户端程序自己实现Authenticator子类用于用户认证
     */
    static class MyAuthenricator extends Authenticator {
        String user = null;
        String pass = "";

        public MyAuthenricator(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pass);
        }
    }

    public static void send(String from, String to, String subject, String content) {
        Properties props = System.getProperties();
        String host = "smtp.exmail.qq.com";
        props.setProperty("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
        String user = "service@lehe8.com";
        String password = "Dengchen@1502";
        Session session = Session.getInstance(props,
                new MyAuthenricator(user, password));
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    to));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //String email = "lgh_26@163.com";
        //System.out.print(StringUtils.isBlank(email));
        String to = "1523156110@qq.com";
        //to = "862286777@qq.com";
        String from = "service@lehe8.com";
        String content = "<body><div style\"font-size:20px;\">"
                + "<span>感谢您使用登辰充值卡回收平台。"
                + "<span>&nbsp;&nbsp;您的验证码为："
                + "<span style=\"font-size: 50px;color:red;\">&nbsp;&nbsp;0000</span></div></body>";
        SendEmail.send(from, to, "登辰充值卡回收", content);
    }


}
