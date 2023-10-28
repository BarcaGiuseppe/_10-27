package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AddDialog extends JDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField ageField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private boolean buttonOKPressed = false;

    public AddDialog(JFrame f, String title) {

        setTitle(title);
        contentPane= new JPanel();
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        nameField = new JTextField(30);
        surnameField = new JTextField(30);
        ageField = new JTextField(30);
        emailField = new JTextField(30);
        phoneNumberField = new JTextField(30);
        initialize();

        setLocationRelativeTo(f);
        setVisible(true);
    }

    private void initialize() {
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(6,1));

        JPanel panelName = new JPanel();//pannello pulsanti
        panelName.setLayout(new FlowLayout());

        JPanel panelSurname = new JPanel();//pannello pulsanti
        panelSurname.setLayout(new FlowLayout());

        JPanel panelAge = new JPanel();//pannello pulsanti
        panelAge.setLayout(new FlowLayout());

        JPanel panelEmail = new JPanel();//pannello pulsanti
        panelEmail.setLayout(new FlowLayout());

        JPanel panelPhoneNumber = new JPanel();//pannello pulsanti
        panelPhoneNumber.setLayout(new FlowLayout());

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());

        setModal(true);

        panelName.add(new JLabel("Enter person's name"));
        panelName.add(nameField);
        panelSurname.add(new JLabel("Enter person's surname"));
        panelSurname.add(surnameField);
        panelAge.add(new JLabel("Enter person's age"));
        panelAge.add(ageField);
        panelEmail.add(new JLabel("Enter person's email"));
        panelEmail.add(emailField);
        panelPhoneNumber.add(new JLabel("Enter person's phone number"));
        panelPhoneNumber.add(phoneNumberField);
        panelButton.add(buttonOK);
        panelButton.add(buttonCancel);

        contentPane.add(panelName);
        contentPane.add(panelSurname);
        contentPane.add(panelAge);
        contentPane.add(panelEmail);
        contentPane.add(panelPhoneNumber);
        contentPane.add(panelButton);

        getRootPane().setDefaultButton(buttonOK);

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

    public JButton getButtonOK() {
        return buttonOK;
    }

    public boolean isButtonOKPressed() {
        return buttonOKPressed;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }
    public JTextField getEmailField() {
        return emailField;
    }
    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }


}
