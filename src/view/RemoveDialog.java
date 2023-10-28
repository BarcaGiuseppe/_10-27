package view;

import model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RemoveDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table;
    private boolean buttonOKPressed = false;
    String[] columnNames = {"ID", "Name", "Surname", "Age", "Email", "PhoneNumber"};

    public RemoveDialog(JFrame f, String title, ArrayList<Person> list) throws ClassNotFoundException {

        setTitle(title);
        contentPane= new JPanel();
        buttonOK = new JButton("Remove");
        buttonCancel = new JButton("Cancel");
        initialize(list);

        setLocationRelativeTo(f);
        setVisible(true);
    }

    private void initialize(ArrayList<Person> persons) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());

        contentPane.setLayout(new GridLayout(2,1));
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
        panelButton.add(buttonOK);
        panelButton.add(buttonCancel);
        contentPane.add(scroll);
        contentPane.add(panelButton);

        buttonOK.addActionListener((ActionEvent e) -> {
            buttonOKPressed = true;
            dispose();
        });

        buttonCancel.addActionListener((ActionEvent e) -> {
            dispose();
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();
    }

    public JTable getTable() {
        return table;
    }

    public boolean isButtonOKPressed() {
        return buttonOKPressed;
    }

}

