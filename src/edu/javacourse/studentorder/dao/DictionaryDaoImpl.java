package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exeption.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static edu.javacourse.studentorder.config.Config.*;

public class DictionaryDaoImpl implements DictionaryDao {

    private final static String GET_STREET = "SELECT street_code, street_name " +
            "FROM jc_street WHERE UPPER(street_name) LIKE UPPER(?)";

    private Connection getConnection() throws SQLException {
//        String user = "postgres";
//        String password = "postgres";
//        String url = "jdbc:postgresql://localhost:5432/jc_student";
//        Connection con = DriverManager.getConnection(url, user, password);
        Connection con = DriverManager.getConnection(
                Config.getProperty(DB_URL),
                Config.getProperty(DB_LOGIN),
                Config.getProperty(DB_PASSWORD));
        System.out.println("Connection successful");
        return con;
    }
    
    public List<Street> findStreets(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_STREET)) {
            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("Preconnection DB");
            while (rs.next()) {
                Street str = new Street(rs.getLong("street_code"),
                        rs.getString("street_name"));
                System.out.println("Connection DB");
                result.add(str);
            }
/*            do {
                Street str = new Street(rs.getLong("street_code"),
                        rs.getString("street_name"));
                System.out.println("Connection DB");
                result.add(str);
            } while (rs.next());*/

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}


