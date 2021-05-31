package guiLab.data;

import guiLab.business.Person;

import java.util.ArrayList;


public interface PersonDAO {

    Person getPerson(String userName, String password);

    boolean addPerson(Person person);

    ArrayList<Person> getPersons();

    boolean isvalidateUserName(String userName);
}
