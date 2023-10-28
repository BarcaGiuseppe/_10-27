package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class UpdateDialog extends JDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private boolean buttonOKPressed = false;

    public UpdateDialog(JFrame f, String title) {

        setTitle(title);
        contentPane = new JPanel();
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        idField = new JTextField(15);
        emailField = new JTextField(30);
        phoneNumberField = new JTextField(30);
        initialize();

        setLocationRelativeTo(f);
        setVisible(true);
    }

    private void initialize() {
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4,1));
        JPanel panelId = new JPanel();
        panelId.setLayout(new FlowLayout());
        JPanel panelEmail = new JPanel();
        panelEmail.setLayout(new FlowLayout());
        JPanel panelPhoneNumber = new JPanel();
        panelPhoneNumber.setLayout(new FlowLayout());
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        setModal(true);

        panelId.add(new JLabel("Enter person's id"));
        panelId.add(idField);
        panelEmail.add(new JLabel("Enter new person's email"));
        panelEmail.add(emailField);
        panelPhoneNumber.add(new JLabel("Enter new person's phone number"));
        panelPhoneNumber.add(phoneNumberField);
        panelButton.add(buttonOK);
        panelButton.add(buttonCancel);

        contentPane.add(panelId);
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

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getEmailField() {
        return emailField;
    }
    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }


}
