package view;

import model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ListDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table;
    private boolean buttonOKPressed = false;
    String[] columnNames = {"ID", "Name", "Surname", "Age", "Email", "PhoneNumber"};

    public ListDialog(JFrame f, String title, ArrayList<Person> list) throws ClassNotFoundException {

        setTitle(title);
        contentPane= new JPanel();
        setLocationRelativeTo(f);
        initialize(list);


        setVisible(true);
    }

    private void initialize(ArrayList<Person> persons) {
        setContentPane(contentPane);
        setModal(true);

        contentPane.setLayout(new BorderLayout());//cambiare con GridLayout all'evenienza
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        if(persons.size() < 1)
        {
            JOptionPane.showMessageDialog(null, "No Record Found","Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            for (Person person : persons) {
                model.addRow(new Object[]{person.getId(), person.getName(), person.getSurname(), String.valueOf(person.getAge()), person.getEmail(), person.getPhoneNumber()});
            }
        }

        contentPane.add(scroll);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();
    }

    public JTable getTable() {
        return table;
    }
}


