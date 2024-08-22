package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exeption.DaoException;

import java.util.List;

public interface DictionaryDao {

    List<Street> findStreets(String pattern) throws DaoException;

}
