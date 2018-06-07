import persistence.Address;
import persistence.Meeting;
import persistence.Person;
import persistence.Place;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample");

        EntityManager entityManager = emf.createEntityManager();

        prepareData(entityManager);

        for (Person p : getAllPersons(entityManager)) {
            System.out.println(p);
        }

        System.out.println(getPersonBySurname(entityManager, "nazwisko"));

        System.out.println(getAllMeetings(entityManager));

        entityManager.close();
        emf.close();
    }

    private static List<Person> getAllPersons(EntityManager entityManager) {
        Query query = entityManager.createQuery("select p from Person p", Person.class);

        return query.getResultList();
    }

    private static Person getPersonBySurname(EntityManager entityManager, String surname) {
        TypedQuery<Person> query =
                entityManager.createQuery("select p from Person p where p.surname = :surname", Person.class);
        query.setParameter("surname", surname);

        return query.getSingleResult();
    }

    private static List<Meeting> getAllMeetings(EntityManager entityManager) {
        Query query = entityManager.createQuery("select m from Meeting m", Meeting.class);

        return query.getResultList();
    }

    private static void prepareData(EntityManager entityManager) {
        Person[] persons = new Person[10];
        Address[] addresses = new Address[5];
        Place[] places = new Place[5];
        Meeting[] meetings = new Meeting[10];

        for (int i = 0; i < persons.length; i++) {
            persons[i] = new Person("Person " + i, "Surname " + i, "phone " + i, "email " + i);
        }

        for (int i = 0; i < addresses.length; i++) {
            addresses[i] = new Address("Street " + i, i, i, "code " + i, "City " + i, "Country " + i);
        }

        for (int i = 0; i < places.length; i++) {
            places[i] = new Place("Place " + i, addresses[i]);
        }

        for (int i = 0; i < meetings.length; i++) {
            meetings[i] = new Meeting(LocalDateTime.now(), "info " + i);

            meetings[i].setMeetingPlace(places[i / 2]);
        }

        List<Person> persons1 = Arrays.asList(persons[0], persons[2], persons[5]);
        List<Person> persons2 = Arrays.asList(persons[6], persons[4], persons[1], persons[2]);
        List<Person> persons3 = Arrays.asList(persons[6], persons[4], persons[8]);

        meetings[2].setPersons(persons1);
        meetings[4].setPersons(persons2);
        meetings[5].setPersons(persons3);

        entityManager.getTransaction().begin();

        for (Person p : persons) {
            entityManager.persist(p);
        }

        for (Place pl : places) {
            entityManager.persist(pl);
        }

        for (Meeting m : meetings) {
            entityManager.persist(m);
        }

        entityManager.getTransaction().commit();
    }
}
