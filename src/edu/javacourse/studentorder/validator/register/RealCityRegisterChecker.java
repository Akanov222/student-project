package edu.javacourse.studentorder.validator.register;

import edu.javacourse.studentorder.exeption.TransportException;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.domain.register.CityRegisterResponse;
import edu.javacourse.studentorder.exeption.CityRegisterException;

public class RealCityRegisterChecker implements CityRegisterChecker{

    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException {
        return null;
    }
}
