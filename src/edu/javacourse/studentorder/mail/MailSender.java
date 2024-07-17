package edu.javacourse.studentorder.mail;

import edu.javacourse.studentorder.domain.StudentOrder;

public class MailSender {
    public StudentOrder sendMail(StudentOrder so) {
        System.out.println("Mail is sending");
        return so;
    }
}
