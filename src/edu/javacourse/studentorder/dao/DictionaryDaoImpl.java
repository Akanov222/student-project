package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exeption.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private final static String GET_STREET = "SELECT street_code, street_name " +
            "FROM jc_street WHERE UPPER(street_name) LIKE UPPER(?)";
    private final static String GET_PASSPORT = "SELECT p_office_id, p_office_area_id, p_office_name " +
            "FROM jc_passport_office WHERE p_office_area_id=?";
    private final static String GET_REGISTER = "SELECT r_office_id, r_office_area_id, r_office_name " +
            "FROM jc_register_office WHERE r_office_area_id=?";
    private final static String GET_AREA = "SELECT * " +
            "FROM jc_country_struct WHERE area_id LIKE ? AND area_id<> ?";

    // TODO refactoring - Make one method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        System.out.println("Connection successful");
        return con;
    }
    
    public List<Street> findStreets(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_STREET)) {
            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("Preconnection1 DB");
            while (rs.next()) {
                Street str = new Street(rs.getLong("street_code"),
                        rs.getString("street_name"));
                System.out.println("Connection1 DB");
                result.add(str);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    public List<PassportOffice> findPassportOffice(String officeAreaId) throws DaoException {
            List<PassportOffice> result = new LinkedList<>();
            try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)) {
                stmt.setString(1, "%" + officeAreaId + "%");
                ResultSet rs = stmt.executeQuery();
                System.out.println("Preconnection2 DB");
                while (rs.next()) {
                    PassportOffice str = new PassportOffice(
                            rs.getLong("p_office_id"),
                            rs.getString("p_office_area_id"),
                            rs.getString("p_office_name"));
                    System.out.println("Connection2 DB");
                    result.add(str);
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
            return result;
    }


    public List<RegisterOffice> findRegisterOffice(String officeAreaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_REGISTER)) {
            stmt.setString(1, "%" + officeAreaId + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("Preconnection3 DB");
            while (rs.next()) {
                RegisterOffice str = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name"));
                System.out.println("Connection3 DB");
                result.add(str);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        List<CountryArea> result = new LinkedList<>();
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_AREA)) {
            String param1 = buildParam(areaId);
            String param2 = areaId;
            stmt.setString(1, param1);
            stmt.setString(2, param2);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Preconnection4 DB");
            while (rs.next()) {
                CountryArea str = new CountryArea(
                        rs.getString("area_id"),
                        rs.getString("area_name"));
                System.out.println("Connection4 DB");
                result.add(str);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private String buildParam(String areaId) throws SQLException {
        if (areaId == null || areaId.trim().isEmpty()) {
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")) {
            return areaId.substring(0,2) + "___0000000";
        } else if (areaId.endsWith("0000000")) {
            return areaId.substring(0,5) + "___0000";
        } else if (areaId.endsWith("0000")) {
            return areaId.substring(0,8) + "____";
        }
        throw new SQLException("Invalid parameter areaId:" + areaId);
    }
}


