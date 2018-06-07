package dao;

import model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao extends BaseDao {
    private static final String ALL_PERSONS = "select name, surname, address, email from Person";
    private static final String PERSON_BY_SURNAME = "select name, surname, address, email from Person where surname = ?";


    public List<Person> getAllPersons() {
        ResultSet rs = null;
        List<Person> personList = new ArrayList();

        try {
            rs = executeQuery(ALL_PERSONS);

            while (rs.next()) {
                personList.add(getNextPersonFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                DbUtils.closeQuietly(rs.getStatement().getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personList;
    }

    public Person getPersonBySurname(String surname) throws SQLException {
        ResultSet resultSet = executeQuery(PERSON_BY_SURNAME, surname);
        return (resultSet.next())? getNextPersonFromResultSet(resultSet) : null;
    }

    private Person getNextPersonFromResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String address = rs.getString("address");
        String email = rs.getString("email");
        return new Person(name, surname, address, email);
    }
}
