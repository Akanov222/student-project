package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.domain.register.AnswerCityRegister;
import edu.javacourse.studentorder.domain.register.AnswerCityRegisterItem;
import edu.javacourse.studentorder.validator.register.CityRegisterChecker;
import edu.javacourse.studentorder.domain.register.CityRegisterResponse;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.validator.register.FakeCityRegisterChecker;
import edu.javacourse.studentorder.exeption.CityRegisterException;

import java.util.List;

public class CityRegisterValidator {

    public String hostName;
    protected  int port;
    private String login;
    String password;

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();
        ans.addItem(checkPerson(so.getHusband()));
        ans.addItem(checkPerson(so.getWife()));
            for (Child child: so.getChildren()) {
                ans.addItem(checkPerson(child));
            }
        return ans;
    }

    private AnswerCityRegisterItem checkPerson(Person person) {
        try {
            CityRegisterResponse hans = personChecker.checkPerson(person);
        } catch (CityRegisterException e){
            e.printStackTrace(System.out);
        }
        return null;
    }
}
