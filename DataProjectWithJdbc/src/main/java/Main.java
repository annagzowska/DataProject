import dao.PersonDao;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PersonDao personDao = new PersonDao();

        System.out.println(personDao.getAllPersons());

        System.out.println(personDao.getPersonBySurname("Nazwisko"));
    }
}
