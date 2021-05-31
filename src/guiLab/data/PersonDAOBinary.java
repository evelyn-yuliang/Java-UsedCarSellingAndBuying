package guiLab.data;

import guiLab.business.Person;

import java.io.*;
import java.util.ArrayList;

public class PersonDAOBinary implements PersonDAO {


    private File personFile = null;

    public PersonDAOBinary() {
        personFile = new File("UserNamePwd.dat");
    }

    private void checkFile() throws IOException {
        if (!personFile.exists()) {
            personFile.createNewFile();
        }
    }

    private boolean savePersons(ArrayList<Person> persons) {
        DataOutputStream out = null;
        try {
            this.checkFile();
            out = new DataOutputStream(new FileOutputStream(personFile));
            for (Person p : persons) {
                out.writeUTF(p.getUserName());
                out.writeUTF(p.getPassword());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public ArrayList<Person> getPersons() {
        // TODO Auto-generated method stub
        DataInputStream in = null;
        ArrayList<Person> persons = new ArrayList<Person>();
        try {
            this.checkFile();
            in = new DataInputStream(new FileInputStream(personFile));
            while (in.available() > 0) {
                String userName = in.readUTF();
                String password = in.readUTF();
                Person p = new Person(userName, password);
                p.setUserName(userName);
                ;
                p.setPassword(password);
                ;
                persons.add(p);
            }
        } catch (EOFException eofe) {
            this.close(in);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return persons;
    }

    private void close(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public boolean addPerson(Person person) {
        // TODO Auto-generated method stub
        ArrayList<Person> persons = this.getPersons();
        persons.add(person);
        return this.savePersons(persons);
    }

    @Override
    public Person getPerson(String userName, String password) {
        ArrayList<Person> persons = this.getPersons();
        for (Person p : persons) {
            if (p.getUserName().equals(userName) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    //Check no duplicate username

    public boolean isvalidateUserName(String userName) {

        ArrayList<Person> persons = this.getPersons();
        for (Person p : persons) {
            if (p.getUserName().equals(userName)) {
                return false;
            }
        }
        return true;

    }


}
