package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.domain.children.AnswerChildren;
import edu.javacourse.studentorder.domain.register.AnswerCityRegister;
import edu.javacourse.studentorder.domain.student.AnswerStudent;
import edu.javacourse.studentorder.domain.wedding.AnswerWedding;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.validator.ChildrenValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;
import edu.javacourse.studentorder.validator.StudentValidator;
import edu.javacourse.studentorder.validator.WeddingValidator;

import java.util.LinkedList;
import java.util.List;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private MailSender mailSender;

    private StudentOrderValidator() {
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();

    }

    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    public void checkAll() {
        List <StudentOrder> soList = readStudentOrder();

        for (StudentOrder so : soList) {
            checkOneOrder(so);
        }
    }

    public List<StudentOrder> readStudentOrder() {
        List <StudentOrder> soList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            StudentOrder so = SaveStudentOrder.buildStudentOrder(i);
            soList.add(so);
        }

        return soList;
    }

        public void checkOneOrder(StudentOrder so) {
            AnswerCityRegister cityAnswer = checkCityRegister(so);
//            AnswerWedding wendAnswer = checkWedding(so);
//            AnswerChildren childrenVal = checkChildren(so);
//            AnswerStudent studAnswer = checkStudent(so);
            sendMail(so);
    }


    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        return cityRegisterVal.checkCityRegister(so);
    }

    public AnswerWedding checkWedding(StudentOrder so) {
        return weddingVal.checkWedding(so);
    }

    public AnswerChildren checkChildren(StudentOrder so) {
        return childrenVal.checkChildren(so);
    }

    public AnswerStudent checkStudent(StudentOrder so) {
        return studentVal.checkStudent(so);
    }

    public StudentOrder sendMail(StudentOrder so) {
        return mailSender.sendMail(so);
    }
}