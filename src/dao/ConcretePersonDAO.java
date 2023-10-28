package dao;

import datasource.ConnectionDBH2;
import model.Person;
import util.Observer;


import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConcretePersonDAO implements Crud<Person> {

    private List<Observer> observers = new ArrayList<>();

    private final ConnectionDBH2 connection_db;

    public ConcretePersonDAO(ConnectionDBH2 connection_db) {
        this.connection_db = connection_db;
    }

    @Override
    public void add(Person person) throws IOException {
        PreparedStatement ps;
        try {

            ps = connection_db.getConnectData().prepareStatement("insert into PERSON(ID, NAME, SURNAME, AGE, EMAIL, PHONENUMBER) values(?,?,?,?,?,?)");
            ps.setString(1, person.getId());
            ps.setString(2, person.getName());
            ps.setString(3, person.getSurname());
            ps.setInt(4, person.getAge());
            ps.setString(5, person.getEmail());
            ps.setString(6, person.getPhoneNumber());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Persona aggiunta correttamente!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    @Override
    public List<Person> findAll() {
        List<Person> listItems = new ArrayList<>();
        try {
            PreparedStatement statement = connection_db.getConnectData()
                    .prepareStatement(" SELECT * FROM PERSON");
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                listItems.add(new Person.Builder<>()
                        .addId(r.getString("id"))
                        .addName(r.getString("name"))
                        .addSurname(r.getString("surname"))
                        .addAge(r.getInt("age"))
                        .addEmail(r.getString("email"))
                        .addPhoneNumber(r.getString("phoneNumber"))
                        .build()
                );
            }
            return listItems;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            return null;
        }
    }

    @Override
    public void update(String id, Person person) {
        String sqlPersonData = "UPDATE person SET email = ?, phonenumber = ? where person.id = ?";
        PreparedStatement ps;
        try {
            ps = connection_db.getConnectData().prepareStatement(sqlPersonData);
            //ps.setString(2, person.getName());
            //ps.setString(3, person.getSurname());
            //ps.setInt(4, person.getAge());
            ps.setString(1, person.getEmail());
            ps.setString(2, person.getPhoneNumber());
            ps.setString(3, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Modificato correttamente!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }

    }

    @Override
    public void delete(String id) {
        PreparedStatement ps;
        try {
            ps = connection_db.getConnectData().prepareStatement("delete from PERSON where PERSON.ID = " + "'" + id + "'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cancellato correttamente!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
}
