package controller;

import dao.ConcretePersonDAO;
import datasource.ConnectionDBH2;
import model.Person;
import util.ConcreteObserver;
import util.Observer;
import util.Subject;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controller implements ActionListener, Subject {

    private Window w;
    private ConnectionDBH2 connectionDBH2;
    private ConcretePersonDAO concretePersonDAO;

    private final List<Observer> observers;

    public Controller(){
        this.w = new Window();
        this.connectionDBH2 = ConnectionDBH2.getInstance();
        this.concretePersonDAO = new ConcretePersonDAO(connectionDBH2);
        this.observers = new ArrayList<>();


        //Aggiungo l'action Listener ai vari bottoni dell' app

        w.getAddButton().addActionListener(this);
        w.getRemoveButton().addActionListener(this);
        w.getUpdateButton().addActionListener(this);
        w.getGetListButton().addActionListener(this);


    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String error = "";
        if (event.getSource() == w.getAddButton()) {
            try {
                error=addPerson();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (event.getSource() == w.getRemoveButton()) {
            try {
                error = removePerson();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (event.getSource() == w.getUpdateButton()) {
            try {
                error = updatePerson();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (event.getSource() == w.getGetListButton()) {
            try {
                error=listPerson();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if(!error.isEmpty()) JOptionPane.showMessageDialog(w.getFrame(), error, "Allert", JOptionPane.WARNING_MESSAGE);
    }

    private String addPerson() throws IOException {

        AddDialog dialog = new AddDialog(w.getFrame(), "New person");
        if(!dialog.isButtonOKPressed()) return "";

        String name = dialog.getNameField().getText().trim();
        String surname = dialog.getSurnameField().getText().trim();
        String age = dialog.getAgeField().getText().trim();
        String email = dialog.getEmailField().getText().trim();
        String phoneNumber = dialog.getPhoneNumberField().getText().trim();

        if(name.isEmpty()) return "Name cannot be empty";
        if(surname.isEmpty()) return "Surname age cannot be empty";
        if(age.isEmpty()) return "Age cannot be empty";
        if(email.isEmpty()) return "Email cannot be empty";
        if(phoneNumber.isEmpty()) return "Phone Number cannot be empty";

        if(!phoneNumber.matches("[0-9+]+")) return "Phone Number not valid!";

        concretePersonDAO.add(new Person.Builder<>()
                .addName(name)
                .addSurname(surname)
                .addAge(Integer.parseInt(age))
                .addEmail(email)
                .addPhoneNumber(phoneNumber)
                .build());

        return "";
    }

    private String removePerson() throws ClassNotFoundException {

        RemoveDialog removeDialog = new RemoveDialog(w.getFrame(), "Remove Person", (ArrayList<Person>) concretePersonDAO.findAll());
        if(!removeDialog.isButtonOKPressed()) return "";
        if(removeDialog.getTable().getSelectedRow() < 0) return "Nessuna persona selezionata";

        Integer c = removeDialog.getTable().getSelectedRow()+1;

        int val = JOptionPane.showConfirmDialog(removeDialog, "Rimuovere persona con id "+c, "Remove Person", JOptionPane.YES_NO_OPTION);
        if(val != JOptionPane.YES_OPTION) return "";

        concretePersonDAO.delete(String.valueOf(c));

        return "";
    }

    private String updatePerson() throws IOException {

        UpdateDialog dialog = new UpdateDialog(w.getFrame(), "Update person");
        if(!dialog.isButtonOKPressed()) return "";

        String id = dialog.getIdField().getText().trim();
        String email = dialog.getEmailField().getText().trim();
        String phoneNumber = dialog.getPhoneNumberField().getText().trim();

        if(id.isEmpty()) return "Id cannot be empty";
        if(email.isEmpty()) return "Email cannot be empty";
        if(phoneNumber.isEmpty()) return "Phone Number cannot be empty";

        if(!phoneNumber.matches("[0-9+]+")) return "Phone Number not valid!";

        concretePersonDAO.update(id, new Person.Builder<>()
                .addName("sample")
                .addSurname("sample")
                .addAge(Integer.parseInt("5"))
                .addEmail(email)
                .addPhoneNumber(phoneNumber)
                .build());
        ConcreteObserver concreteObserver = new ConcreteObserver.Builder()
                .setEmailPerson(email)
                .setPhoneNumberPerson(phoneNumber)
                .setIdPerson(id)
                .build();
        this.register(concreteObserver);
        this.notifyObservers();
        return "";
    }

    private String listPerson() throws ClassNotFoundException {

        ListDialog removeDialog = new ListDialog(w.getFrame(), "List Person", (ArrayList<Person>) concretePersonDAO.findAll());

        return "";
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }
}
