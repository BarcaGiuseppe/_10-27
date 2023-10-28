package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;
import java.awt.Container;
import java.awt.FlowLayout;

public class Window {
    JFrame  frame;
    JButton addB;
    JButton removeB;
    JButton updateB;
    JButton getListB;
    public Window() {
        frame = new JFrame("App Person");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addB = new JButton("Add");
        removeB = new JButton("Remove");
        updateB = new JButton("Update");
        getListB = new JButton("GetList");

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(4,1));




        JPanel panelAdd = new JPanel();//pannello pulsanti
        panelAdd.setLayout(new FlowLayout());
        JPanel panelRemove = new JPanel();//pannello pulsanti
        panelRemove.setLayout(new FlowLayout());
        JPanel panelUpdate = new JPanel();//pannello pulsanti
        panelUpdate.setLayout(new FlowLayout());
        JPanel panelGetList = new JPanel();//pannello pulsanti
        panelGetList.setLayout(new FlowLayout());


        panelAdd.add(addB);
        panelAdd.add(new JLabel("Add new person into DB"));
        panelRemove.add(removeB);
        panelRemove.add(new JLabel("Remove a person from DB"));
        panelUpdate.add(updateB);
        panelUpdate.add(new JLabel("Update person into DB"));
        panelGetList.add(getListB);
        panelGetList.add(new JLabel("Get list of person from DB"));


        contentPane.add(panelAdd);
        contentPane.add(panelRemove);
        contentPane.add(panelUpdate);
        contentPane.add(panelGetList);


        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //La dimensione è impostata come la minima necessaria a visualizzare tutti i controlli
        frame.setLocation((int) ((dim.getWidth()-frame.getWidth())/2), (int) ((dim.getHeight()-frame.getHeight())/2));//Sposta la finestra al centro dello schermo con il metodo setLocation(int x,int y) dove x,y sono le coordinate dell’angolo in alto a sinistra
        frame.setVisible(true);

    }

    public JButton getAddButton() {
        return addB;
    }
    public JButton getRemoveButton() {
        return removeB;
    }
    public JButton getUpdateButton() {
        return updateB;
    }
    public JButton getGetListButton() {
        return getListB;
    }

    public JFrame getFrame() {
        return frame;
    }

}
