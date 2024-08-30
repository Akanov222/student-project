package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exeption.DaoException;

public interface StudentOrderDao {

    Long saveStudentOrder(StudentOrder studentOrder) throws DaoException;
}
