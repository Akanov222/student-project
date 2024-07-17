package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.ChildrenValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;
import edu.javacourse.studentorder.validator.StudentValidator;
import edu.javacourse.studentorder.validator.WeddingValidator;

public class StudentOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }

    static void checkAll() {
        while (true) {
            StudentOrder so = readStudentOrder();
            if (so == null) {
                break;
            }

            AnswerCityRegister cityAnswer = checkCityRegister(so);
            if (!cityAnswer.success) {
                break;
            }

            AnswerWedding wendAnswer = checkWedding(so);
            AnswerChildren childAnswer = checkChildren(so);
            AnswerStudent studAnswer = checkStudent(so);

            sendMail(so);
        }
    }

    static StudentOrder readStudentOrder() {
        StudentOrder so = new StudentOrder();
        return so;
    }

    static AnswerCityRegister checkCityRegister(StudentOrder so) {
        CityRegisterValidator crv1 = new CityRegisterValidator();
        crv1.hostName = "Host1";
        AnswerCityRegister ans1 = crv1.checkCityRegister(so);
        return ans1;
    }

    static AnswerWedding checkWedding(StudentOrder so) {
        WeddingValidator wd = new WeddingValidator();
        return wd.checkWedding(so);
    }

    static AnswerChildren checkChildren(StudentOrder so) {
        ChildrenValidator cd = new ChildrenValidator();
        return cd.checkChildren(so);
    }

    static AnswerStudent checkStudent(StudentOrder so) {
        return new StudentValidator().checkStudent(so);
    }

    static StudentOrder sendMail(StudentOrder so) {
        return new MailSender().sendMail(so);
    }
}